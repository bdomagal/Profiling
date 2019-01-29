package com.example.demo.repositories;

import com.example.demo.persistance.Tag;
import com.example.demo.persistance.UserPreference;
import com.example.demo.persistance.UserPreferencePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, UserPreferencePK> {
    UserPreference findByIdUserAndTagName(int idUser, String tagName);

    Collection<UserPreference> findByIdUser(Integer idUser);
}
