package com.example.moviesandbooksrecommendationsservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecsController {
    @GetMapping("/main")
    public String index() {
        return "index.html";
    }

    @GetMapping("/movies")
    public String getRecommendations() {
        return "movies.html";
    }
}
