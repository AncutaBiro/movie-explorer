package org.fasttrackit.movieexplorer.steps;

import org.fasttrackit.movieexplorer.service.CategoryService;
import org.fasttrackit.movieexplorer.transfer.category.CategoryResponse;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class CategoryTestSteps {

    @Autowired
    private CategoryService categoryService;

    public CategoryResponse createCategory() {
        SaveCategoryRequest request = new SaveCategoryRequest();
        request.setGenre("fantasy");

        CategoryResponse category = categoryService.createCategory(request);
        assertThat(category, notNullValue());
        assertThat(category.getId(), greaterThan(0L));
        assertThat(category.getGenre(), is(request.getGenre()));

        return category;
    }
}

