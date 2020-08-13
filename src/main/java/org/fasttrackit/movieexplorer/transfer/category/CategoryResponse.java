package org.fasttrackit.movieexplorer.transfer.category;

import java.util.List;

public class CategoryResponse {

    private long id;
    private String genre;
    private String poster;
    private List<MovieInCategoryResponse> movies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<MovieInCategoryResponse> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieInCategoryResponse> movies) {
        this.movies = movies;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
