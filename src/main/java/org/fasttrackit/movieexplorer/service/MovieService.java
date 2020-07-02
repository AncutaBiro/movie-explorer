package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.persistence.MovieRepository;
import org.fasttrackit.movieexplorer.transfer.SaveMovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(SaveMovieRequest request) {
        LOGGER.info("Creating product {}", request);

        Movie movie = new Movie();
        movie.setName(request.getName());
        movie.setDescription(request.getDescription());
        movie.setPoster(request.getPoster());
        movie.setTrailer(request.getTrailer());

        return movieRepository.save(movie);
    }

    public Movie getMovie(long id) {
        LOGGER.info("Retrieving movie {}", id);

        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (movieOptional.isPresent()) {
            return movieOptional.get();
        } else {
            throw new ResourceNotFoundException("Movie " + id + " not found.");
        }

    }

    public Movie updateMovie(long id, SaveMovieRequest request) {
        LOGGER.info("Updating movie {} : {}", id, request);

        Movie movie = getMovie(id);

        BeanUtils.copyProperties(request, movie);

        return movieRepository.save(movie);

    }

    public void deleteMovie(long id) {
        LOGGER.info("Deleting movie {}", id);

        movieRepository.deleteById(id);
    }

}
