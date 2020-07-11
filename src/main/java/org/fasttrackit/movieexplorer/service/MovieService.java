package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.persistence.MovieRepository;
import org.fasttrackit.movieexplorer.transfer.GetMoviesRequest;
import org.fasttrackit.movieexplorer.transfer.SaveMovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setPoster(request.getPoster());
        movie.setTrailer(request.getTrailer());
        movie.setGenre(request.getGenre());
        movie.setLeadingRole(request.getLeadingRole());
        movie.setRate(request.getRate());

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

    public Page<Movie> getMoviesBy(GetMoviesRequest request, Pageable pageable) {
        if (request.getPartialTitle() != null) {
            return movieRepository.findByTitleContaining(request.getPartialTitle(), pageable);
        } else if (request.getFindGenre() != null) {
            return movieRepository.findByGenre(request.getFindGenre(), pageable);
        } else if (request.getFindLeadingRole() != null) {
            return movieRepository.findByLeadingRole(request.getFindLeadingRole(), pageable);
        } else if (request.getFindRate() != null) {
            return movieRepository.findByRateGreaterThan(request.getFindRate(), pageable);
        } else {
            return movieRepository.findAll(pageable);
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
