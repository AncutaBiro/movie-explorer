package org.fasttrackit.movieexplorer.transfer.category;

import java.util.List;

public class CategoryResponse {

    private long id;
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
}
