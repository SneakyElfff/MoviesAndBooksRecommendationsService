package com.example.moviesandbooksrecommendationsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<String> authors;

    @JsonProperty("description")
    private String description;

    @JsonProperty("publishedDate")
    private String publishedDate;

    @JsonProperty("categories")
    private List<String> categories;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("maturityRating")
    private String maturityRating;

    @JsonProperty("pageCount")
    private int pageCount;

    @JsonProperty("imageLinks")
    private Thumbnail thumbnail;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnail {
        @JsonProperty("thumbnail")
        private String thumbnail;

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}