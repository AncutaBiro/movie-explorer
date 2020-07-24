package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface MovieRepository extends JpaRepository <Movie, Long> {

    Page<Movie> findByTitleContaining(String partialTitle, Pageable pageable);

    Page<Movie> findByAverageRateGreaterThanEqual(BigDecimal findAverageRate, Pageable pageable);

    //JPQL syntax
    @Query(value= "SELECT movie FROM Movie movie WHERE " +
            "(:partialTitle IS null OR movie.title LIKE %:partialTitle%) AND " +
            "(:partialDescription IS null OR movie.description LIKE %:partialDescription%) ")
    Page<Movie> findByOptionalCriteria (String partialTitle, String partialDescription, Pageable pageable);

}
