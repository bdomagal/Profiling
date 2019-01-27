package com.example.demo.controllers;


import com.example.demo.dto.UserDto;
import com.example.demo.persistance.User;
import com.example.demo.persistance.UserProfile;
import com.example.demo.repositories.UserProfileRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MyController {

    @Autowired private UserProfileRepository userProfileRepository;
    @Autowired private UserRepository userRepository;


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
}
