package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.persistence.MovieRepository;
import org.fasttrackit.movieexplorer.transfer.SaveMovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie (SaveMovieRequest request) {
        LOGGER.info("Creating product {}", request);

        Movie movie= new Movie();
        movie.setName(request.getName());
        movie.setDescription(request.getDescription());
        movie.setPoster(request.getPoster());
        movie.setTrailer(request.getTrailer());

        return movieRepository.save(movie);
    }
}
