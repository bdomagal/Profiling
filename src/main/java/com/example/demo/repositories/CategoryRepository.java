package com.example.demo.repositories;

import com.example.demo.persistance.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Tag, String> {
}
