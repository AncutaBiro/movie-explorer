package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.domain.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


public interface RatingRepository extends JpaRepository <Rating, Long> {

    Page <Rating> findByMovieId (long movieId, Pageable pageable);

}
