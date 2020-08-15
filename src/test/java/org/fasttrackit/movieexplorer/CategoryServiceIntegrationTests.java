package org.fasttrackit.movieexplorer;

import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.service.CategoryService;
import org.fasttrackit.movieexplorer.steps.CategoryTestSteps;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.MatcherAssert.assertThat;

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
    void getCategory_whenInvalidRequest_thenThrowException() {
        try {
            categoryService.getMoviesByCategory("comedy", Pageable.unpaged());
        } catch (Exception e) {
            assertThat(("Unexpected exception"), e instanceof ResourceNotFoundException);
        }

    }

}
