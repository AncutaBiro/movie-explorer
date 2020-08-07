package org.fasttrackit.movieexplorer.transfer.category;

import java.math.BigDecimal;

public class MovieInCategoryResponse {

    private long id;
    private String title;
    private BigDecimal rate;

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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
