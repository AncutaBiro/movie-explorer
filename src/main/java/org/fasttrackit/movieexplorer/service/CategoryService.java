package org.fasttrackit.movieexplorer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.persistence.CategoryRepository;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    private final ObjectMapper objectMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ObjectMapper objectMapper, CategoryRepository categoryRepository) {
        this.objectMapper = objectMapper;
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory (SaveCategoryRequest request)  {
        LOGGER.info ("Creating category {}", request);

//        Category category =  new Category();
//        category.setGenre(request.getGenre());

        Category category = objectMapper.convertValue (request, Category.class);

        return categoryRepository.save(category);
    }

    public Category getCategory (long id) {
        LOGGER.info("Retrieving category {}", id);

        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category " + id + " not found."));
    }


}
