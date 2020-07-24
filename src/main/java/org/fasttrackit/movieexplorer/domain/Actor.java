package org.fasttrackit.movieexplorer.domain;

public class Actor {

    private long id;
    private String fullName;
    private String starringMovies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStarringMovies() {
        return starringMovies;
    }

    public void setStarringMovies(String starringMovies) {
        this.starringMovies = starringMovies;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", starringMovies='" + starringMovies + '\'' +
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