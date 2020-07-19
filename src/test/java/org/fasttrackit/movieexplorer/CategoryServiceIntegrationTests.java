package org.fasttrackit.movieexplorer;

import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.service.CategoryService;
import org.fasttrackit.movieexplorer.steps.CategoryTestSteps;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class CategoryServiceIntegrationTests {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryTestSteps categoryTestSteps;

    @Test
    void createCategory_whenCorrectRequest_thenReturnCratedCategory() {
        categoryTestSteps.createCategory();
    }

    @Test
    void createCategory_whenMissingMandatoryProperties_thenThrowExceptions() {
        SaveCategoryRequest request = new SaveCategoryRequest();

        try {
            categoryService.createCategory(request);
        } catch (Exception e) {
            assertThat(("Unexpected exception"), e instanceof ConstraintViolationException);
        }
    }

    @Test
    void getCategory_whenValidRequest_thenReturnCategory() {
        Category category = categoryTestSteps.createCategory();

        Category response = categoryService.getCategory(category.getId());

        assertThat(response, notNullValue());
        assertThat(response.getId(), is(category.getId()));
        assertThat(response.getGenre(), is(category.getGenre()));

    }

    @Test
    void getCategory_whenInvalidRequest_thenThrowException() {
        try {
            categoryService.getCategory(0);
        } catch (Exception e) {
            assertThat(("Unexpected exception"), e instanceof ResourceNotFoundException);
        }

    }

}
