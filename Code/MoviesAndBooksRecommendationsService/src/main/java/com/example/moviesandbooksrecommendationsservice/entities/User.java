package com.example.moviesandbooksrecommendationsservice.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ElementCollection
    @CollectionTable(name = "user_movie_list", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "movie_list", unique = true)
    private Set<String> movieList = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_book_list", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "book_list", unique = true)
    private Set<String> bookList = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_book_list", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "book_list")
    @Column(name = "isbn_list")
    private Map<String, String> bookIsbnMap = new HashMap<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMovieList(Set<String> movieList) {
        this.movieList = movieList;
    }

    public Map<String, String> getBookIsbnMap() {
        return bookIsbnMap;
    }

    public void setBookIsbnMap(Map<String, String> bookIsbnMap) {
        this.bookIsbnMap = bookIsbnMap;
    }
}