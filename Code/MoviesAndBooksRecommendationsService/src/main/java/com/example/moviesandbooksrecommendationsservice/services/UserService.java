package com.example.moviesandbooksrecommendationsservice.services;

import com.example.moviesandbooksrecommendationsservice.entities.User;
import com.example.moviesandbooksrecommendationsservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean addMovieToUserList(String movieTitle, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
        }

        Set<String> movieList = user.getMovieList();
        if (!movieList.contains(movieTitle)) {
            movieList.add(movieTitle);
            userRepository.save(user);

            return true;
        }

        return false;
    }

    @Transactional
    public boolean addBookToUserList(String bookTitle, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
        }

        Set<String> bookList = user.getBookList();
        if (!bookList.contains(bookTitle)) {
            bookList.add(bookTitle);
            userRepository.save(user);

            return true;
        }

        return false;
    }
}