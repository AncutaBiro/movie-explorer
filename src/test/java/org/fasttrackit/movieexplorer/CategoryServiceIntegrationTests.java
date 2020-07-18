package org.fasttrackit.movieexplorer;

import org.fasttrackit.movieexplorer.service.CategoryService;
import org.fasttrackit.movieexplorer.steps.CategoryTestSteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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






}
