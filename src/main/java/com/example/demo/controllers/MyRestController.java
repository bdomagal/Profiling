package com.example.demo.controllers;

import com.example.demo.persistance.UserBook;
import com.example.demo.persistance.UserPreference;
import com.example.demo.persistance.UserProfile;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyRestController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    UserPreferenceRepository userPreferenceRepository;
    @Autowired
    UserBookRepository userBookRepository;

    @GetMapping("book/{id}/getTime")
    public int getTime(Authentication authentication, @PathVariable("id") int id){
        UserProfile up = userProfileRepository.findByUsername(authentication.getName());
        UserBook ub = userBookRepository.findAllByIdBookAndIdUser(id, up.getIdUser());
        return ub.getTimeSpentOnPage();
    }

    @PostMapping("book/{id}/setTime")
    public int setTime(Authentication authentication, @PathVariable("id") int id, @RequestBody int time){
        UserProfile up = userProfileRepository.findByUsername(authentication.getName());
        UserBook ub = userBookRepository.findAllByIdBookAndIdUser(id, up.getIdUser());
        ub.setTimeSpentOnPage(time);
        userBookRepository.save(ub);
        return ub.getTimeSpentOnPage();
    }


}
