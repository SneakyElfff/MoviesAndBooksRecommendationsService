package com.example.moviesandbooksrecommendationsservice.services;

import com.example.moviesandbooksrecommendationsservice.entities.User;
import com.example.moviesandbooksrecommendationsservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addMovieToUserList(String movieTitle, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
        }
        user.getMovieList().add(movieTitle);
        userRepository.save(user);
    }

    @Transactional
    public void addBookToUserList(String bookTitle, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
        }
        user.getBookList().add(bookTitle);
        userRepository.save(user);
    }
}