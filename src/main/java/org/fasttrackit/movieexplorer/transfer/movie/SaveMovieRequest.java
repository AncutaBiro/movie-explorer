package org.fasttrackit.movieexplorer.transfer.movie;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SaveMovieRequest {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String poster;
    private String trailer;
    private BigDecimal averageRate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public BigDecimal getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(BigDecimal averageRate) {
        this.averageRate = averageRate;
    }

    @Override
    public String toString() {
        return "SaveMovieRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", trailer='" + trailer + '\'' +
                ", averageRate=" + averageRate +
                '}';
    }
}


