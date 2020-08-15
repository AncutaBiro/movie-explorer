package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.domain.Actor;
import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.persistence.ActorRepository;
import org.fasttrackit.movieexplorer.transfer.actor.ActorResponse;
import org.fasttrackit.movieexplorer.transfer.actor.AddMovieToActorRequest;
import org.fasttrackit.movieexplorer.transfer.actor.MovieInActorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service

public class ActorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);

    private final ActorRepository actorRepository;
    private final MovieService movieService;

    @Autowired
    public ActorService(ActorRepository actorRepository, MovieService movieService) {
        this.actorRepository = actorRepository;
        this.movieService = movieService;
    }

    @Transactional
    public void addMovieToActor(long actorId, AddMovieToActorRequest request) {
        LOGGER.info("Adding movie to actor {} {}", actorId, request);

        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Actor " + actorId + " not found"));

        for (Long movieId : request.getMoviesIds()) {
            Movie movie = movieService.getMovie(movieId);
            actor.addMovie(movie);
        }
        actorRepository.save(actor);
    }

    @Transactional
    public ActorResponse getActor(long id) {
        LOGGER.info("Retrieving actor {}", id);

        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor " + id + " not found"));

        ActorResponse actorResponse = new ActorResponse();
        actorResponse.setId(actor.getId());
        actorResponse.setFirstName(actor.getFirstName());
        actorResponse.setLastName(actor.getLastName());

        List<MovieInActorResponse> movieDtos = new ArrayList<>();

        for (Movie movie : actor.getMovies()) {
            MovieInActorResponse movieResponse = new MovieInActorResponse();
            movieResponse.setId(movie.getId());
            movieResponse.setTitle(movie.getTitle());
            movieResponse.setRate(movie.getRate());

            movieDtos.add(movieResponse);
        }
        actorResponse.setMovies(movieDtos);

        return actorResponse;
    }

}




