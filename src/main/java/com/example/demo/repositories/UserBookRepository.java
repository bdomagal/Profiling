package com.example.demo.repositories;

import com.example.demo.persistance.User;
import com.example.demo.persistance.UserBook;
import com.example.demo.persistance.UserBookPK;
import com.example.demo.persistance.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;

public interface UserBookRepository extends JpaRepository<UserBook, UserBookPK> {
    UserBook findAllByIdBookAndIdUser(int bookId, int userId);

    ArrayList<UserBook> findAllByIdUser(Integer idUser);
}
