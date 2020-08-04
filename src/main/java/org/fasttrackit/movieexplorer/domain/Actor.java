package org.fasttrackit.movieexplorer.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String firstNameA;

    @NotNull
    private String lastNameA;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "actor_movie",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new HashSet<>();

    public void addMovie (Movie movie) {
        movies.add(movie);
        movie.getActors().add(this);
    }

    public void removeMovie (Movie movie) {
        movies.remove(movie);
        movie.getActors().remove(this);
    }

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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstNameA='" + firstNameA + '\'' +
                ", lastNameA='" + lastNameA + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        return id == actor.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}