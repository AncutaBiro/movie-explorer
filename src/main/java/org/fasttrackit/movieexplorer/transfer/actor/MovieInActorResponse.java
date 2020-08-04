package org.fasttrackit.movieexplorer.transfer.actor;

import java.math.BigDecimal;

public class MovieInActorResponse {

    private long id;
    private String title;
    private BigDecimal averageRate;

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

    public BigDecimal getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(BigDecimal averageRate) {
        this.averageRate = averageRate;
    }
}
