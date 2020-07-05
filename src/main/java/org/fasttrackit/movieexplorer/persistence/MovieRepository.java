package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByNameContaining(String partialName, Pageable pageable);

    Page<Movie> findByLeadingRole(String findLeadingRole, Pageable pageable);

    Page<Movie> findByGenre(String findGenre, Pageable pageable);

}
