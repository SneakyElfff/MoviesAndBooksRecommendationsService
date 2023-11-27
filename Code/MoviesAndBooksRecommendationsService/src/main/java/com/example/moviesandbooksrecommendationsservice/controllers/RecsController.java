package com.example.moviesandbooksrecommendationsservice.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecsController {
    @GetMapping("/main")
    public String index() {
        return "index.html";
    }

    @GetMapping("/movies")
    public String movies() {
        return "movies.html";
    }

    @GetMapping("/books")
    public String books() {
        return "books.html";
    }

    @GetMapping("/signIn")
    public String signIn(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/account";    //вход выполнен, перейти на страницу аккаунта
        }
        return "signIn.html";
    }

    @GetMapping("/account")
    public String account() { return "account.html"; }
}