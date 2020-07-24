package org.fasttrackit.movieexplorer.transfer.movie;

import java.math.BigDecimal;

public class GetMoviesRequest {

    String partialTitle;
    BigDecimal findAverageRate;
    String partialDescription;

    public String getPartialTitle() {
        return partialTitle;
    }

    public void setPartialTitle(String partialTitle) {
        this.partialTitle = partialTitle;
    }

    public BigDecimal getFindAverageRate() {
        return findAverageRate;
    }

    public void setFindAverageRate(BigDecimal findAverageRate) {
        this.findAverageRate = findAverageRate;
    }

    public String getPartialDescription() {
        return partialDescription;
    }

    public void setPartialDescription(String partialDescription) {
        this.partialDescription = partialDescription;
    }

    @Override
    public String toString() {
        return "GetMoviesRequest{" +
                "partialTitle='" + partialTitle + '\'' +
                ", findAverageRate=" + findAverageRate +
                ", partialDescription='" + partialDescription + '\'' +
                '}';
    }
}
