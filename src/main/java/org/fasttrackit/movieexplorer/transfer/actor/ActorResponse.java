package org.fasttrackit.movieexplorer.transfer.actor;

import java.util.List;

public class ActorResponse {

    private long id;
    private String firstName;
    private String lastName;
    private List<MovieInActorResponse> movies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<MovieInActorResponse> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieInActorResponse> movies) {
        this.movies = movies;
    }
}
