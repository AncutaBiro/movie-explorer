package org.fasttrackit.movieexplorer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.persistence.CategoryRepository;
import org.fasttrackit.movieexplorer.persistence.MovieRepository;
import org.fasttrackit.movieexplorer.transfer.category.CategoryResponse;
import org.fasttrackit.movieexplorer.transfer.category.MovieInCategoryResponse;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.fasttrackit.movieexplorer.transfer.category.AddMovieToCategoryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    private final ObjectMapper objectMapper;
    private final CategoryRepository categoryRepository;
    private final MovieService movieService;

    @Autowired
    public CategoryService(ObjectMapper objectMapper, CategoryRepository categoryRepository, MovieService movieService) {
        this.objectMapper = objectMapper;
        this.categoryRepository = categoryRepository;
        this.movieService = movieService;
    }

    @Transactional
    public void addMovieToCategory(long categoryId, AddMovieToCategoryRequest request) {
        LOGGER.info("Adding movie to category {} {}", categoryId, request);

        Category category = categoryRepository.findById(categoryId).orElseThrow();

        for (Long movieId : request.getMovieIds()) {
            Movie movie = movieService.getMovie(movieId);
            category.addMovie(movie);
        }
        categoryRepository.save(category);
    }

    @Transactional
    public CategoryResponse createCategory(SaveCategoryRequest request) {
        LOGGER.info("Creating category {}", request);
        Category category = new Category();
        category.setGenre(request.getGenre());

        Category savedCategory = categoryRepository.save(category);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setGenre(category.getGenre());

        return mapCategoryResponse(savedCategory);
    }

    @Transactional
    public CategoryResponse getCategory(long id) {
        LOGGER.info("Retrieving category {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category " + id + " not found."));

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setGenre(category.getGenre());

        List<MovieInCategoryResponse> movieDtos = new ArrayList<>();

        for (Movie movie : category.getMovies()) {
            MovieInCategoryResponse movieResponse = new MovieInCategoryResponse();
            movieResponse.setId(movie.getId());
            movieResponse.setTitle(movie.getTitle());
            movieResponse.setAverageRate(movie.getAverageRate());

            movieDtos.add(movieResponse);
        }
        categoryResponse.setMovies(movieDtos);

        return categoryResponse;
    }

    private CategoryResponse mapCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setGenre(category.getGenre());

        return categoryResponse;
    }


}


