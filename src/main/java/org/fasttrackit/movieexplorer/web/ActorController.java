package org.fasttrackit.movieexplorer.web;

import org.fasttrackit.movieexplorer.service.ActorService;
import org.fasttrackit.movieexplorer.transfer.actor.ActorResponse;
import org.fasttrackit.movieexplorer.transfer.actor.AddMovieToActorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/actors")

public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> addMovieToActor (@PathVariable long id, @RequestBody @Valid AddMovieToActorRequest request) {
       actorService.addMovieToActor(id, request);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponse> getActor (@PathVariable long id) {
        ActorResponse actor = actorService.getActor(id);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }



}
