package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
