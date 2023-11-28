package com.example.moviesandbooksrecommendationsservice.controllers;

import com.example.moviesandbooksrecommendationsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestParam String movieTitle, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            userService.addMovieToUserList(movieTitle, authentication.getName());
            return ResponseEntity.ok("Movie added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }

    @GetMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestParam String bookTitle, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            userService.addBookToUserList(bookTitle, authentication.getName());
            return ResponseEntity.ok("Book added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }
}