package org.fasttrackit.movieexplorer.web;

import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.service.CategoryService;
import org.fasttrackit.movieexplorer.transfer.category.AddMovieToCategoryRequest;
import org.fasttrackit.movieexplorer.transfer.category.CategoryResponse;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid SaveCategoryRequest request) {
        CategoryResponse category = categoryService.createCategory(request);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> addMovieToCategory(@PathVariable long id,
                                                   @RequestBody @Valid AddMovieToCategoryRequest request) {
        categoryService.addMovieToCategory(id, request);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable long id) {
        CategoryResponse category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping ("/{genre}")
    public ResponseEntity<CategoryResponse> getMoviesByCategory (@PathVariable String genre, Pageable pageable) {
        CategoryResponse category = categoryService.getMoviesByCategory (genre, pageable);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}

