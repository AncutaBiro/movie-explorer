package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.persistence.MovieRepository;
import org.fasttrackit.movieexplorer.transfer.movie.GetMoviesRequest;
import org.fasttrackit.movieexplorer.transfer.movie.MovieResponse;
import org.fasttrackit.movieexplorer.transfer.movie.SaveMovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponse createMovie(SaveMovieRequest request) {
        LOGGER.info("Creating movie {}", request);

        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setPoster(request.getPoster());
        movie.setTrailer(request.getTrailer());
        movie.setRate(request.getRate());

        Movie movie1 = movieRepository.save(movie);

        return mapMovieResponse(movie1);
    }

    public MovieResponse getMovieResponse(long id) {
        LOGGER.info("Retrieving movie {}", id);

        Movie movie = getMovie(id);

        return mapMovieResponse(movie);
    }

    public Movie getMovie(long id) {
        //        Optional<Movie> movieOptional = movieRepository.findById(id);
//        if (movieOptional.isPresent()) {
//            return movieOptional.get();
//        } else {
//            throw new ResourceNotFoundException("Movie " + id + " not found.");
//        }
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie " + id + " not found."));
    }

    @Transactional
    public Page<MovieResponse> getMoviesByRate (Pageable pageable) {
//        if (request.getPartialTitle() != null) {
//            return movieRepository.findByTitleContaining(request.getPartialTitle(), pageable);
//        } else if (request.getFindAverageRate() != null) {
//            return movieRepository.findByAverageRateGreaterThanEqual(request.getFindAverageRate(), pageable);
//        } else {
//            return movieRepository.findAll(pageable);
//        }
        Page<Movie> page = movieRepository.findAllByOrderByRateDesc (pageable);

        List<MovieResponse> moviesDtos = new ArrayList<>();

        for (Movie movie : page.getContent()) {
            MovieResponse movieResponse = mapMovieResponse(movie);
            moviesDtos.add(movieResponse);
        }
        return new PageImpl<>(moviesDtos, pageable, page.getTotalElements());
    }

    @Transactional
    public Page<MovieResponse> getMoviesBy(GetMoviesRequest request, Pageable pageable) {
//        if (request.getPartialTitle() != null) {
//            return movieRepository.findByTitleContaining(request.getPartialTitle(), pageable);
//        } else if (request.getFindAverageRate() != null) {
//            return movieRepository.findByAverageRateGreaterThanEqual(request.getFindAverageRate(), pageable);
//        } else {
//            return movieRepository.findAll(pageable);
//        }
        Page<Movie> page = movieRepository
                .findByOptionalCriteria(request.getPartialTitle(), request.getPartialDescription(), pageable);

        List<MovieResponse> moviesDtos = new ArrayList<>();

        for (Movie movie : page.getContent()) {
            MovieResponse movieResponse = mapMovieResponse(movie);
            moviesDtos.add(movieResponse);
        }

        return new PageImpl<>(moviesDtos, pageable, page.getTotalElements());
    }

    public MovieResponse updateMovie(long id, SaveMovieRequest request) {
        LOGGER.info("Updating movie {} : {}", id, request);

        Movie movie = getMovie(id);

//        String [] excludedProperties = new String["title", ];

        BeanUtils.copyProperties(request, movie);

        Movie movie1 = movieRepository.save(movie);

        return mapMovieResponse(movie1);
    }

    public void deleteMovie(long id) {
        LOGGER.info("Deleting movie {}", id);
        movieRepository.deleteById(id);
    }


    private MovieResponse mapMovieResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId());
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setDescription(movie.getDescription());
        movieResponse.setPoster(movie.getPoster());
        movieResponse.setTrailer(movie.getTrailer());
        movieResponse.setRate(movie.getRate());
        movieResponse.setCategories(mapCategories(movie.getCategories()));

        return movieResponse;
    }

    private List<String> mapCategories (Set<Category> categories) {
        return categories.stream().map(Category::getGenre).collect(Collectors.toList());
    }
}
