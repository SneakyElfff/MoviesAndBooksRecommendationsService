package com.example.moviesandbooksrecommendationsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    @JsonProperty("alternativeName")
    private String alternativeName;

    @JsonProperty("year")
    private int year;

    @JsonProperty("description")
    private String description;

    @JsonProperty("movieLength")
    private int movieLength;

    @JsonProperty("ratingMpaa")
    private String ratingMpaa;

    @JsonProperty("genres")
    private List<Genre> genres;

    @JsonProperty("countries")
    private List<Country> countries;

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public String getRatingMpaa() {
        return ratingMpaa;
    }

    public void setRatingMpaa(String ratingMpaa) {
        this.ratingMpaa = ratingMpaa;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Genre {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Country {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}