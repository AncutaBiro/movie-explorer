package org.fasttrackit.movieexplorer.transfer.movie;

import java.math.BigDecimal;

public class UpdateMovieRequest {

    private BigDecimal rate;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "SaveMovieRequest{" +
                "rate=" + rate +
                '}';
    }

}
