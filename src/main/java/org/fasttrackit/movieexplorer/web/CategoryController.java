package org.fasttrackit.movieexplorer.web;

import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.service.CategoryService;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/categories")

public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory (@RequestBody @Valid SaveCategoryRequest request){
        Category category = categoryService.createCategory(request);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory (@PathVariable long id) {
        Category category = categoryService.getCategory(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
