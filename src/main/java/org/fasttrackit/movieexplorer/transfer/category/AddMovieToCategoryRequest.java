package org.fasttrackit.movieexplorer.transfer.category;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddMovieToCategoryRequest {

    @NotNull
    private List<Long> movieIds;

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }

    @Override
    public String toString() {
        return "SortMovieToCategory{" +
                "movieIds=" + movieIds +
                '}';
    }
}
