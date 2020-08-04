package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.domain.Rating;
import org.fasttrackit.movieexplorer.persistence.RatingRepository;
import org.fasttrackit.movieexplorer.transfer.rating.RatingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingService.class);
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    public Page<RatingResponse> getRatings (long movieId, Pageable pageable) {
        Page<Rating> page = ratingRepository.findByMovieId(movieId, pageable);

        List<RatingResponse> ratingDtos = new ArrayList<>();

        for (Rating rating: page.getContent()) {
            RatingResponse ratingResponse = mapRatingResponse(rating);

            ratingDtos.add(ratingResponse);
        }
         return new PageImpl<>(ratingDtos, pageable, page.getTotalElements());

    }


    private RatingResponse mapRatingResponse (Rating rating) {
        RatingResponse ratingResponse = new RatingResponse();
        ratingResponse.setId(rating.getId());
        ratingResponse.setRate(rating.getRate());

        return ratingResponse;
    }

}
