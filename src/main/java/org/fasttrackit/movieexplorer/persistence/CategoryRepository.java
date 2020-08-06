package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByGenre (String genre, Pageable pageable);

}
