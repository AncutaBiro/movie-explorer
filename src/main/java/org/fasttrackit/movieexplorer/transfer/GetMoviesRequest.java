package org.fasttrackit.movieexplorer.transfer;

public class GetMoviesRequest {

    String partialName;
    String findGenre;
    String findLeadingRole;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
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

    @Override
    public String toString() {
        return "GetMoviesRequest{" +
                "partialName='" + partialName + '\'' +
                ", findGenre='" + findGenre + '\'' +
                ", findLeadingRole='" + findLeadingRole + '\'' +
                '}';
    }
}
