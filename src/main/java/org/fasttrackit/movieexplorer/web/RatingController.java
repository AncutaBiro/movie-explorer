package org.fasttrackit.movieexplorer.web;


import org.fasttrackit.movieexplorer.service.RatingService;
import org.fasttrackit.movieexplorer.transfer.rating.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping ("/ratings")

public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<RatingResponse>> getRatings (@PathVariable long id, Pageable pageable) {
        Page<RatingResponse> ratings = ratingService.getRatings(id, pageable);

        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }












}
