package org.fasttrackit.movieexplorer.transfer.movie;

import java.math.BigDecimal;

public class GetMoviesRequest {

    String partialTitle;
    String findGenre;
    String findLeadingRole;
    BigDecimal findRate;

    public String getPartialTitle() {
        return partialTitle;
    }

    public void setPartialTitle(String partialTitle) {
        this.partialTitle = partialTitle;
    }

    public String getFindGenre() {
        return findGenre;
    }

    public void setFindGenre(String findGenre) {
        this.findGenre = findGenre;
    }

    public String getFindLeadingRole() {
        return findLeadingRole;
    }

    public void setFindLeadingRole(String findLeadingRole) {
        this.findLeadingRole = findLeadingRole;
    }

    public BigDecimal getFindRate() {
        return findRate;
    }

    public void setFindRate(BigDecimal findRate) {
        this.findRate = findRate;
    }

    @Override
    public String toString() {
        return "GetMoviesRequest{" +
                "partialTitle='" + partialTitle + '\'' +
                ", findGenre='" + findGenre + '\'' +
                ", findLeadingRole='" + findLeadingRole + '\'' +
                ", findRate=" + findRate +
                '}';
    }
}
