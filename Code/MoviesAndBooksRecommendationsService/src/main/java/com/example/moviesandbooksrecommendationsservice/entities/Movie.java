package com.example.moviesandbooksrecommendationsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    @JsonProperty("id")
    private int id;

    @JsonProperty("alternativeName")
    private String alternativeName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("year")
    private int year;

    @JsonProperty("genres")
    private List<Genre> genres;

    @JsonProperty("countries")
    private List<Country> countries;

    private String director;

    private List<String> actors;

    @JsonProperty("ratingMpaa")
    private String ratingMpaa;

    @JsonProperty("movieLength")
    private int movieLength;

    @JsonProperty("poster")
    private Poster poster;

    public void setId(int id) {
        this.id = id;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setRatingMpaa(String ratingMpaa) {
        this.ratingMpaa = ratingMpaa;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Genre {
        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Country {
        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Poster {
        @JsonProperty("url")
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }
    }
}