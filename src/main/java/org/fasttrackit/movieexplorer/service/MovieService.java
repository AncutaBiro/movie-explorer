package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.persistence.MovieRepository;
import org.fasttrackit.movieexplorer.transfer.SaveMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie (SaveMovieRequest request) {
        System.out.println("Creating movie: " + request);

        Movie movie= new Movie();
        movie.setName(request.getName());
        movie.setDescription(request.getDescription());
        movie.setPoster(request.getPoster());
        movie.setTrailer(request.getTrailer());

        return movieRepository.save(movie);
    }
}
