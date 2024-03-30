package com.example.moviesandbooksrecommendationsservice.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
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

    @JsonProperty("industryIdentifiers")
    private List<IndustryIdentifier> industryIdentifiers;

    @JsonProperty("isbn")
    private String isbn;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnail {
        @JsonProperty("thumbnail")
        private String thumbnail;

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndustryIdentifier {
        @JsonProperty("type")
        private String type;

        @JsonProperty("identifier")
        private String identifier;
    }

    @JsonSetter("industryIdentifiers")
    public void setIsbn(List<IndustryIdentifier> industryIdentifiers) {
        for (IndustryIdentifier identifier : industryIdentifiers) {
            if ("ISBN_13".equals(identifier.getType())) {
                this.isbn = identifier.getIdentifier();
                return;
            }
        }

        this.isbn = null;
    }
}