package com.example.demo.repositories;

import com.example.demo.persistance.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    UserProfile findByUsername(String username);
}
