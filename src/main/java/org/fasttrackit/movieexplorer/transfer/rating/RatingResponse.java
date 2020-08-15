package org.fasttrackit.movieexplorer.transfer.rating;

import java.math.BigDecimal;

public class RatingResponse {

    private long id;
    private BigDecimal rate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
