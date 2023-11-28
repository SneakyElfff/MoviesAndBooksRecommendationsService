package com.example.moviesandbooksrecommendationsservice.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ElementCollection
    private List<String> movieList = new ArrayList<>();

    @ElementCollection
    private List<String> bookList = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMovieList(List<String> movieList) {
        this.movieList = movieList;
    }

    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }
}