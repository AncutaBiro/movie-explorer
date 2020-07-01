package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
