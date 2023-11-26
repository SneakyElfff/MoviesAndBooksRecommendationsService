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
import java.util.ArrayList;
import java.util.List;

@RestController
public class MoviesController {
    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    private static final String KINOPOISK_API_URL = "https://api.kinopoisk.dev/v1.4/movie/search?page=1&limit=1&query=";

    @GetMapping("/searchMovie")
    public ResponseEntity<Movie> getMovieInfo(@RequestParam String movieTitle) {
        RestTemplate template = new RestTemplate();

        String url = KINOPOISK_API_URL + movieTitle;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "D54AW5N-TKP4NKF-K1WTGKY-XD5Y3JS");
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);

        //преобразование JSON в объект Movie
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            JsonNode movieNode = root.path("docs").get(0);     //получить первый объект фильма из массива "docs"

            Movie movie = objectMapper.treeToValue(movieNode, Movie.class);

            String id = String.valueOf(movieNode.path("id").asLong());

            String url_staff = "https://kinopoiskapiunofficial.tech/api/v1/staff?filmId=" + id;

            headers.set("X-API-KEY", "a48fa905-cbc9-4031-ac7d-be116c3a1a53");
            headers.set("Accept", "application/json");

            HttpEntity<String> entity_staff = new HttpEntity<>(headers);

            ResponseEntity<String> response_staff = template.exchange(url_staff, HttpMethod.GET, entity_staff, String.class);

            JsonNode root_staff = objectMapper.readTree(response_staff.getBody());

            List<String> actorsList = new ArrayList<>();
            int actorsCount = 0;

            for (JsonNode staffNode : root_staff) {
                if ("DIRECTOR".equals(staffNode.path("professionKey").asText())) {
                    movie.setDirector(staffNode.path("nameEn").asText());
                }

                if ("ACTOR".equals(staffNode.path("professionKey").asText())) {
                    actorsList.add(staffNode.path("nameEn").asText());
                    actorsCount++;
                    if (actorsCount >= 5) {
                        break;
                    }
                }
            }
            movie.setActors(actorsList);

            logger.info("Deserialized movie: {}", movie);
            return ResponseEntity.ok(movie);
        } catch (IOException e) {
            logger.error("Error deserializing movie", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
