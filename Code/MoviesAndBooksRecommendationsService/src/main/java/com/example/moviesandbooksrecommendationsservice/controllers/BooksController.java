package com.example.moviesandbooksrecommendationsservice.controllers;

import com.example.moviesandbooksrecommendationsservice.entities.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import java.io.IOException;
import java.util.Random;

@RestController
public class BooksController {
    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    private static final String GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @GetMapping("/searchBook")
    public ResponseEntity<Book> getBookInfo(@RequestParam String bookTitle, @RequestParam(required = false) String authorName) {
        RestTemplate template = new RestTemplate();

        String url = GOOGLE_BOOKS_API_URL + bookTitle + "+intitle:" + bookTitle + "+inauthor:" + authorName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "AIzaSyCKmxvPEfqkhJObL5Byce24XmKvmTuo2lY");
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            
            Book book = null;
            if (root.path("totalItems").asInt() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(book);
            JsonNode book_node = root.path("items").get(0).path("volumeInfo");

            book = objectMapper.treeToValue(book_node, Book.class);

            logger.info("Deserialized book: {}", book);
            return ResponseEntity.ok(book);
        } catch (IOException e) {
            logger.error("Error deserializing book", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/recommendBooks")
    public ResponseEntity<Book> getRecentBooks() {
        RestTemplate template = new RestTemplate();

        String url = GOOGLE_BOOKS_API_URL + "-subject:Mathematics" + "&orderBy=newest" + "&printType=books";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "AIzaSyCKmxvPEfqkhJObL5Byce24XmKvmTuo2lY");
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            Book book = null;
            int total = root.path("totalItems").asInt();
            if (total == 0)
                return ResponseEntity.ok(book);

            int ind = new Random().nextInt(10);    //выбрать случайный индекс

            JsonNode book_node = root.path("items").get(ind).path("volumeInfo");

            book = objectMapper.treeToValue(book_node, Book.class);

            logger.info("Deserialized book: {}", book);
            return ResponseEntity.ok(book);
        } catch (IOException e) {
            logger.error("Error deserializing book", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}