package com.example.demo.repositories;

import com.example.demo.persistance.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
