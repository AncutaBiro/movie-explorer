package org.fasttrackit.movieexplorer.transfer.category;

import javax.validation.constraints.NotNull;

public class SaveCategoryRequest {

    @NotNull
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "SaveCategoryRequest{" +
                "genre='" + genre + '\'' +
                '}';
    }
}
