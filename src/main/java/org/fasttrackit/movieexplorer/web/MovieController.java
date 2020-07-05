package org.fasttrackit.movieexplorer.web;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.service.MovieService;
import org.fasttrackit.movieexplorer.transfer.SaveMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody SaveMovieRequest request) {
        Movie movie = movieService.createMovie(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @Valid @RequestBody SaveMovieRequest request) {
        Movie movie = movieService.updateMovie(id, request);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


}
