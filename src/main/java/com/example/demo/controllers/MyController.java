package com.example.demo.controllers;


import com.example.demo.dto.BookDTO;
import com.example.demo.dto.TagDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.persistance.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class MyController {

    @Autowired private UserProfileRepository userProfileRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private BookRepository bookRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired
    UserPreferenceRepository userPreferenceRepository;
    @Autowired UserBookRepository userBookRepository;



    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerUser(@ModelAttribute("user")UserDto user, Model model){
        UserProfile up = user.getProfile();
        User u = new User();
        u.setIsRegistered(true);
        u=userRepository.save(u);
        up.setIdUser(u.getIdUser());
        userProfileRepository.save(up);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String showHomePage(Model model, Authentication authentication){
        boolean loggedIn = authentication!=null && authentication.isAuthenticated();
        model.addAttribute("loggedIn", loggedIn);
        UserProfile userProfile = userProfileRepository.findByUsername(authentication.getName());
        User user = userProfile.getUserByIdUser();
        ArrayList<BookDTO> dtos = BookDTO.convertToDtos(bookRepository.findAll());
        Collection<UserBook> preferences = userBookRepository.findAllByIdUser(user.getIdUser());
        for (BookDTO dto : dtos) {
            for (UserBook preference : preferences) {
                if(preference.getIdBook()==dto.getId()){
                    dto.setAvoid(preference.isMarkedAsNotInterested());
                    dto.setLiked(preference.isMarkedAsToRead());
                    dto.setTimeSpent(preference.getTimeSpentOnPage());
                    dto.setTimesVisited(preference.getTimesVisited());
                }
            }
        }
        System.out.println(dtos.size());
        model.addAttribute("books", dtos);
        return "home";
    }

    @GetMapping("/categories")
    public String showCategoriesPage(Model model, Authentication authentication){
        boolean loggedIn = authentication!=null && authentication.isAuthenticated();
        model.addAttribute("loggedIn", loggedIn);
        Collection<Tag> tags = categoryRepository.findAll();
        UserProfile user = userProfileRepository.findByUsername(authentication.getName());
        Collection<UserPreference> preferences = userPreferenceRepository.findByIdUser(user.getIdUser());
        Collection<User> users = userRepository.findAll();
        Collection<UserPreference> nearest = null;
        int distance = Integer.MAX_VALUE;
        for (User neigh : users) {
            if(neigh.getIdUser()!=user.getIdUser()){
                Collection<UserPreference> preferences2 = userPreferenceRepository.findByIdUser(neigh.getIdUser());
                if(preferences2!=null && !preferences2.isEmpty()) {
                    int temp = getDistance(preferences, preferences2);
                    if (temp < distance) {
                        nearest = preferences2;
                        distance = temp;
                    }
                }
            }
        }
        System.out.println(distance);
        ArrayList<TagDTO> result = new ArrayList<>();
        for (Tag tag : tags) {
            TagDTO dto = new TagDTO();
            dto.setName(tag.getTagName());
            for (UserPreference preference : nearest) {
                if(preference.getTagName().equals(tag.getTagName())){
                    dto.setRecommended(preference.getIsLiked());
                }
            }
            for (UserPreference preference : preferences) {
                if(preference.getTagName().equals(tag.getTagName())){
                    dto.setLiked(preference.getIsLiked());
                    dto.setRecommended(dto.isRecommended() && !preference.getIsMarkedToAvoid());
                    dto.setAvoid(preference.getIsMarkedToAvoid());
                }
            }
            result.add(dto);
        }
        model.addAttribute("tags", result);
        return "categories";
    }

    private int getDistance(Collection<UserPreference> preferences, Collection<UserPreference> preferences1) {
        int result = 0;
        ArrayList<String> likes1 = new ArrayList();
        ArrayList<String> dislikes = new ArrayList<>();
        for (UserPreference preference : preferences) {
            if(preference.getIsLiked()){
                likes1.add(preference.getTagName());
            }
            if(preference.getIsMarkedToAvoid()){
                dislikes.add(preference.getTagName());
            }
        }
        ArrayList<String> likes2 = new ArrayList();
        ArrayList<String> temp = new ArrayList<>();
        for (UserPreference preference : preferences1) {
            if(preference.getIsLiked()){
                likes2.add(preference.getTagName());
                temp.add(preference.getTagName());
            }
        }
        likes1.removeAll(temp);
        dislikes.retainAll(likes2);
        result = likes1.size() + dislikes.size();
        return result;

    }

    @GetMapping("category/{name}/liked")
    public String likeCategory(Authentication authentication, @PathVariable("name") String name, @RequestParam("liked") boolean isLike){
        if(authentication==null || !authentication.isAuthenticated()){
            return "error";
        }
        UserProfile user = userProfileRepository.findByUsername(authentication.getName());
        if(user==null){
            return "error";
        }
        UserPreference up = userPreferenceRepository.findByIdUserAndTagName(user.getIdUser(), name);
        if(up == null){
            up = new UserPreference();
            up.setIdUser(user.getIdUser());
            up.setTagName(name);
        }
        up.setIsMarkedToAvoid(false);
        up.setIsLiked(isLike);
        userPreferenceRepository.save(up);
        return "redirect:/categories";
    }

    @GetMapping("category/{name}/avoid")
    public String avoidCategory(Authentication authentication, @PathVariable("name") String name, @RequestParam("avoid") boolean isAvoid){
        if(authentication==null || !authentication.isAuthenticated()){
            return "error";
        }
        UserProfile user = userProfileRepository.findByUsername(authentication.getName());
        if(user==null){
            return "error";
        }
        UserPreference up = userPreferenceRepository.findByIdUserAndTagName(user.getIdUser(), name);
        if(up == null){
            up = new UserPreference();
            up.setIdUser(user.getIdUser());
            up.setTagName(name);
        }
        up.setIsLiked(false);
        up.setIsMarkedToAvoid(isAvoid);
        userPreferenceRepository.save(up);
        return "redirect:/categories";
    }

    @GetMapping("book/{name}/avoid")
    public String avoidBook(Authentication authentication, @PathVariable("name") int id, @RequestParam("avoid") boolean isAvoid){
        if(authentication==null || !authentication.isAuthenticated()){
            return "error";
        }
        UserProfile user = userProfileRepository.findByUsername(authentication.getName());
        if(user==null){
            return "error";
        }
        UserBook up = userBookRepository.findAllByIdBookAndIdUser(id, user.getIdUser());
        if(up == null){
            up = new UserBook();
            up.setIdUser(user.getIdUser());
            up.setIdBook(id);
            up.setTimeSpentOnPage(0);
            up.setTimesVisited(0);
        }
        up.setMarkedAsToRead(false);
        up.setMarkedAsNotInterested(isAvoid);
        userBookRepository.save(up);
        return "redirect:/";
    }

    @GetMapping("book/{name}/liked")
    public String likeBook(Authentication authentication, @PathVariable("name") int id, @RequestParam("liked") boolean isLike){
        if(authentication==null || !authentication.isAuthenticated()){
            return "error";
        }
        UserProfile user = userProfileRepository.findByUsername(authentication.getName());
        if(user==null){
            return "error";
        }
        UserBook up = userBookRepository.findAllByIdBookAndIdUser(id, user.getIdUser());
        if(up == null){
            up = new UserBook();
            up.setIdUser(user.getIdUser());
            up.setIdBook(id);
            up.setTimeSpentOnPage(0);
            up.setTimesVisited(0);
        }
        up.setMarkedAsToRead(isLike);
        up.setMarkedAsNotInterested(false);
        userBookRepository.save(up);
        return "redirect:/";
    }

    @GetMapping("book/{id}")
    public String showBook(Model model, Authentication authentication, @PathVariable("id") int id){
        boolean loggedIn = authentication!=null && authentication.isAuthenticated();
        model.addAttribute("loggedIn", loggedIn);
        UserProfile user = userProfileRepository.findByUsername(authentication.getName());
        UserBook up = userBookRepository.findAllByIdBookAndIdUser(id, user.getIdUser());
        if(up == null){
            Book b = bookRepository.findById(id).get();
            up = new UserBook();
            up.setIdUser(user.getIdUser());
            up.setIdBook(id);
            up.setBookByIdBook(b);
            up.setTimeSpentOnPage(0);
            up.setTimesVisited(0);
            up.setMarkedAsToRead(false);
            up.setMarkedAsNotInterested(false);
        }
        up.setTimesVisited(up.getTimesVisited()+1);
        userBookRepository.save(up);
        model.addAttribute("book", up.getBookByIdBook());
        return "book";
    }
}
