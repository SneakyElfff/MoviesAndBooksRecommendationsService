package com.example.moviesandbooksrecommendationsservice.controllers;

import com.example.moviesandbooksrecommendationsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Set;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestParam String movieTitle, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            boolean added = userService.addMovieToUserList(movieTitle, authentication.getName());
            if (added) {
                return ResponseEntity.ok("Movie added successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This movie is already in the collection");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }

    @GetMapping("/deleteMovie")
    public ResponseEntity<String> deleteMovie(@RequestParam String movieTitle, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            boolean deleted = userService.deleteMovieFromUserList(movieTitle, authentication.getName());
            if (deleted) {
                return ResponseEntity.ok("Movie deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This movie is not in the collection");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }

    @GetMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestParam String bookTitle, @RequestParam String bookIsbn, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            boolean added = userService.addBookToUserList(bookTitle, bookIsbn, authentication.getName());
            if (added) {
                return ResponseEntity.ok("Book added successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This book is already in the collection");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }

    @GetMapping("/deleteBook")
    public ResponseEntity<String> deleteBook(@RequestParam String bookTitle, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            boolean deleted = userService.deleteBookFromUserList(bookTitle, authentication.getName());
            if (deleted) {
                return ResponseEntity.ok("Book deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This book is not in the collection");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }

    @GetMapping("/getMoviesList")
    public ResponseEntity<Set<String>> getUserMovies(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Set<String> movies = userService.getUserMovies(username);

            return ResponseEntity.ok(movies);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/getBooksList")
    public ResponseEntity<Map<String, String>> getUserBooks(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Map<String, String> books = userService.getUserBooks(username);

            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}