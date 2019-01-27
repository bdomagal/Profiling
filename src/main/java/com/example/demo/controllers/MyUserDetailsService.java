package com.example.demo.controllers;

import com.example.demo.persistance.UserProfile;
import com.example.demo.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
  
    @Autowired
    private UserProfileRepository userRepository;
    // 
    public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
  
        UserProfile user = userRepository.findByUsername(username);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return  new org.springframework.security.core.userdetails.User
          (user.getUsername(),
          "{noop}"+user.getPassword().toLowerCase(), enabled, accountNonExpired,
          credentialsNonExpired, accountNonLocked, 
          new ArrayList<>());
    }
}