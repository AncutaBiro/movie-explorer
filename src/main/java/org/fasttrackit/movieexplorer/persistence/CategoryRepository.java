package org.fasttrackit.movieexplorer.persistence;

import org.fasttrackit.movieexplorer.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
