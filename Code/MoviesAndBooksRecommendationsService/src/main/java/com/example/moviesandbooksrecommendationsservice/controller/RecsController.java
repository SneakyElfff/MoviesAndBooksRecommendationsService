package com.example.moviesandbooksrecommendationsservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecsController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
