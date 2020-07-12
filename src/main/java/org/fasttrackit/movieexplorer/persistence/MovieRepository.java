package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByTitleContaining(String partialTitle, Pageable pageable);

    Page<Movie> findByLeadingRole(String findLeadingRole, Pageable pageable);

    Page<Movie> findByGenre(String findGenre, Pageable pageable);

    Page<Movie> findByRateGreaterThanEqual(BigDecimal findRate, Pageable pageable);

    //JPQL syntax
    @Query(value= "SELECT movie FROM Movie movie WHERE " +
            "(:partialTitle IS null OR movie.title LIKE %:partialTitle%) AND " +
            "(:findRate IS null OR movie.rate >= :findRate) ")
    Page<Movie> findByOptionalCriteria (String partialTitle, BigDecimal findRate, Pageable pageable);

}
