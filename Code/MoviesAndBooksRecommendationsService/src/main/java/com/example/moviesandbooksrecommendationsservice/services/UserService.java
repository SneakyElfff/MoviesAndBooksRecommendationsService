package com.example.moviesandbooksrecommendationsservice.services;

import com.example.moviesandbooksrecommendationsservice.entities.User;
import com.example.moviesandbooksrecommendationsservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
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
    public boolean addBookToUserList(String bookTitle, String bookAuthor, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
        }

        Set<String> bookList = user.getBookList();
        Set<String> bookAuthors = user.getBookAuthors();

        if (!bookList.contains(bookTitle)) {
            bookList.add(bookTitle);
            bookAuthors.add(bookAuthor);
            userRepository.save(user);

            return true;
        }

        return false;
    }

    @Transactional
    public Set<String> getUserMovies(String username) {
        User user = userRepository.findByUsername(username);

        return user != null ? new HashSet<>(user.getMovieList()) : Collections.emptySet();
    }

    @Transactional
    public Set<String> getUserBooks(String username) {
        User user = userRepository.findByUsername(username);

        return user != null ? new HashSet<>(user.getBookList()) : Collections.emptySet();
    }

    @Transactional
    public Set<String> getUserBooksAuthors(String username) {
        User user = userRepository.findByUsername(username);

        return user != null ? new HashSet<>(user.getBookAuthors()) : Collections.emptySet();    }
}