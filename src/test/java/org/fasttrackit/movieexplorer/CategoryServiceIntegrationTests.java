package org.fasttrackit.movieexplorer;

import org.fasttrackit.movieexplorer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceIntegrationTests {

    @Autowired
    private final CategoryService categoryService;

    public CategoryServiceIntegrationTests(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
