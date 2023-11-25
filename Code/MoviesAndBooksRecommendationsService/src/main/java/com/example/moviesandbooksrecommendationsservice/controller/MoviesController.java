package com.example.moviesandbooksrecommendationsservice.controller;

import com.example.moviesandbooksrecommendationsservice.entities.Movie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class MoviesController {
    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    private static final String KINOPOISK_API_URL = "https://api.kinopoisk.dev/v1.4/movie/search?page=1&limit=1&query=";

    @GetMapping("/searchMovie")
    public ResponseEntity<Movie> getMovieInfo(@RequestParam String movieTitle) {
        RestTemplate restTemplate = new RestTemplate();
        String url = KINOPOISK_API_URL + movieTitle;

        // Создайте экземпляр HttpHeaders и установите токен
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "D54AW5N-TKP4NKF-K1WTGKY-XD5Y3JS");
        headers.set("Accept", "application/json");

        // Создайте экземпляр HttpEntity с заголовками
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Выполните запрос с использованием HttpEntity
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Преобразование JSON в объект Movie
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            JsonNode movieNode = root.path("docs").get(0);     //получить первый объект фильма из массива "docs"

            Movie movie = objectMapper.treeToValue(movieNode, Movie.class);

            logger.info("Deserialized movie: {}", movie);
            return ResponseEntity.ok(movie);
        } catch (IOException e) {
            logger.error("Error deserializing movie", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
