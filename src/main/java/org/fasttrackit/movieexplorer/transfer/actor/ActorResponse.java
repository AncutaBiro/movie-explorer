package org.fasttrackit.movieexplorer.transfer.actor;

import java.util.List;

public class ActorResponse {

    private long id;
    private String firstNameA;
    private String lastNameA;
    private List <MovieInActorResponse> movies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstNameA() {
        return firstNameA;
    }

    public void setFirstNameA(String firstNameA) {
        this.firstNameA = firstNameA;
    }

    public String getLastNameA() {
        return lastNameA;
    }

    public void setLastNameA(String lastNameA) {
        this.lastNameA = lastNameA;
    }

    public List<MovieInActorResponse> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieInActorResponse> movies) {
        this.movies = movies;
    }
}
