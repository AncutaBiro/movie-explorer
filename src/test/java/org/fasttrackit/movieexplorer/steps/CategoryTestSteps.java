package org.fasttrackit.movieexplorer.steps;

import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.service.CategoryService;
import org.fasttrackit.movieexplorer.service.MovieService;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.fasttrackit.movieexplorer.transfer.movie.SaveMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class CategoryTestSteps {

    @Autowired
    private CategoryService categoryService;

    public Category createCategory() {
        SaveCategoryRequest request = new SaveCategoryRequest();
        request.setGenre("fantasy");

        Category category = categoryService.createCategory(request);
        assertThat(category, notNullValue());
        assertThat(category.getId(), greaterThan(0L));
        assertThat(category.getGenre(), is(request.getGenre()));

        return category;
    }
}

