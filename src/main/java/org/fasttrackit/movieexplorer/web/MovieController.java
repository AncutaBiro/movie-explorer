package org.fasttrackit.movieexplorer.web;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.service.MovieService;
import org.fasttrackit.movieexplorer.transfer.movie.GetMoviesRequest;
import org.fasttrackit.movieexplorer.transfer.movie.MovieResponse;
import org.fasttrackit.movieexplorer.transfer.movie.SaveMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody SaveMovieRequest request) {
        MovieResponse movie = movieService.createMovie(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable long id, @Valid @RequestBody SaveMovieRequest request) {
        MovieResponse movie = movieService.updateMovie(id, request);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable long id) {
        MovieResponse movie = movieService.getMovieResponse(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getMoviesBy(@Valid GetMoviesRequest request, Pageable pageable) {
        Page<MovieResponse> movie = movieService.getMoviesBy(request, pageable);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/byRate")
    public ResponseEntity<Page<MovieResponse>> getMoviesByRate (Pageable pageable) {
        Page<MovieResponse> movie = movieService.getMoviesByRate (pageable);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
