package org.fasttrackit.movieexplorer.transfer.movie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    private long id;
    private String title;
    private String description;
    private String poster;
    private String trailer;
    private BigDecimal rate;

    private List<String> categories = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", trailer='" + trailer + '\'' +
                ", rate=" + rate +
                '}';
    }
}
