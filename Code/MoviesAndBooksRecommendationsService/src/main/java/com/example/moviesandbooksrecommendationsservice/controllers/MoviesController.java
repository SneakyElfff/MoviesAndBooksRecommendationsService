package com.example.moviesandbooksrecommendationsservice.controllers;

import com.example.moviesandbooksrecommendationsservice.entities.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MoviesController {
    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    private static final String KINOPOISK_API_URL = "https://api.kinopoisk.dev/v1.4/movie/search?page=1&limit=1&query=";

    private static final String KINOPOISK_API_URL_MULTIPLE = "https://api.kinopoisk.dev/v1.4/movie/search?page=1&limit=3&query=";

    private static final String KINOPOISK_API_URL_FILTER = "https://api.kinopoisk.dev/v1.4/movie?page=1&limit=3";

    @GetMapping("/searchMovie")
    public ResponseEntity<Movie> getMovieInfo(@RequestParam String movieTitle) {
        RestTemplate template = new RestTemplate();

        String url = KINOPOISK_API_URL + movieTitle;

        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-API-KEY", "D54AW5N-TKP4NKF-K1WTGKY-XD5Y3JS");
        headers.set("X-API-KEY", "CMQKPHR-D1N4494-G2J9T67-2TXV7AK");
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);

        //преобразование JSON в объект Movie
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode movie_node = root.path("docs").get(0);     //получить первый объект фильма из массива "docs"

            Movie movie = objectMapper.treeToValue(movie_node, Movie.class);
            if (movie_node == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(movie);

            movie = getStaffInfo(movie);

            logger.info("Deserialized movie: {}", movie);
            return ResponseEntity.ok(movie);
        } catch (IOException e) {
            logger.error("Error deserializing movie", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/searchMovies")
    public ResponseEntity<List<Movie>> getMoviesInfo(@RequestParam Map<String, String> params) {
        RestTemplate template = new RestTemplate();

        StringBuilder url = new StringBuilder(KINOPOISK_API_URL_FILTER);

        if (StringUtils.hasText(params.get("movieTitle"))) {
            url = new StringBuilder(KINOPOISK_API_URL_MULTIPLE + params.get("movieTitle"));
        } else {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                if (!"movieTitle".equals(key) && StringUtils.hasText(value)) {
                    url.append("&").append(key).append("=").append(value);
                }
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "CMQKPHR-D1N4494-G2J9T67-2TXV7AK");
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(url.toString(), HttpMethod.GET, entity, String.class);

        //преобразование JSON в список объектов Movie
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode moviesNode = root.path("docs");    //получить массив фильмов
            List<Movie> movies = new ArrayList<>();

            for (JsonNode movieNode : moviesNode) {
                Movie movie = objectMapper.treeToValue(movieNode, Movie.class);
                movie = getStaffInfo(movie);
                movies.add(movie);
            }

            if (movies.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(movies);

            logger.info("Deserialized movies: {}", movies);
            return ResponseEntity.ok(movies);
        } catch (IOException e) {
            logger.error("Error deserializing movies", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public Movie getStaffInfo(Movie movie) throws JsonProcessingException {
        RestTemplate template = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-API-KEY", "D54AW5N-TKP4NKF-K1WTGKY-XD5Y3JS");
        headers.set("X-API-KEY", "CMQKPHR-D1N4494-G2J9T67-2TXV7AK");
        headers.set("Accept", "application/json");

        String id = String.valueOf(movie.getId());

        String url_staff = "https://kinopoiskapiunofficial.tech/api/v1/staff?filmId=" + id;

//        headers.set("X-API-KEY", "a48fa905-cbc9-4031-ac7d-be116c3a1a53");
        headers.set("X-API-KEY", "4433492c-5e67-4886-afdb-52c8d5512456");
        headers.set("Accept", "application/json");

        HttpEntity<String> entity_staff = new HttpEntity<>(headers);

        ResponseEntity<String> response_staff = template.exchange(url_staff, HttpMethod.GET, entity_staff, String.class);

        JsonNode root_staff = objectMapper.readTree(response_staff.getBody());

        List<String> actorsList = new ArrayList<>();
        int actorsCount = 0;

        for (JsonNode staff : root_staff) {
            if ("DIRECTOR".equals(staff.path("professionKey").asText())) {
                movie.setDirector(staff.path("nameEn").asText());
            }

            if ("ACTOR".equals(staff.path("professionKey").asText())) {
                actorsList.add(staff.path("nameEn").asText());
                actorsCount++;
                if (actorsCount >= 5) {
                    break;
                }
            }
        }
        movie.setActors(actorsList);

        return movie;
    }

    @GetMapping("/recommendMovies")
    public ResponseEntity<Movie> getRecentMovies() {
        RestTemplate template = new RestTemplate();

        String url = "https://api.kinopoisk.dev/v1.4/movie/random?notNullFields=alternativeName&type=movie&year=" + LocalDate.now().getYear() + "&rating.kp=6-10";

        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-API-KEY", "D54AW5N-TKP4NKF-K1WTGKY-XD5Y3JS");
        headers.set("X-API-KEY", "CMQKPHR-D1N4494-G2J9T67-2TXV7AK");
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);

        //преобразование JSON в объект Movie
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            Movie movie = objectMapper.treeToValue(root, Movie.class);

            movie = getStaffInfo(movie);

            logger.info("Deserialized movie: {}", movie);
            return ResponseEntity.ok(movie);
        } catch (IOException e) {
            logger.error("Error deserializing movie", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}