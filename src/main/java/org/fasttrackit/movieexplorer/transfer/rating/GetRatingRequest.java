package org.fasttrackit.movieexplorer.transfer.rating;

import java.math.BigDecimal;

public class GetRatingRequest {

    private BigDecimal findRate;

    public BigDecimal getFindRate() {
        return findRate;
    }

    public void setFindRate(BigDecimal findRate) {
        this.findRate = findRate;
    }

    @Override
    public String toString() {
        return "GetRatingRequest{" +
                "findRate=" + findRate +
                '}';
    }
}
