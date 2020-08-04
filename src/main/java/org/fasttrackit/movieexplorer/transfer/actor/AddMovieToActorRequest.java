package org.fasttrackit.movieexplorer.transfer.actor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddMovieToActorRequest {

    @NotNull
    private List<Long> moviesIds;

    public List<Long> getMoviesIds() {
        return moviesIds;
    }

    public void setMoviesIds(List<Long> moviesIds) {
        this.moviesIds = moviesIds;
    }

    @Override
    public String toString() {
        return "AddMovieToActorRequest{" +
                "moviesIds=" + moviesIds +
                '}';
    }
}
