package org.fasttrackit.movieexplorer.transfer;

import javax.validation.constraints.NotNull;

public class SaveMovieRequest {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String poster;
    private String trailer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return "SaveMovieRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", trailer='" + trailer + '\'' +
                '}';
    }
}
