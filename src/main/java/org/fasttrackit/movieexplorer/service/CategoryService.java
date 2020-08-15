package org.fasttrackit.movieexplorer.service;

import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.persistence.CategoryRepository;
import org.fasttrackit.movieexplorer.transfer.category.AddMovieToCategoryRequest;
import org.fasttrackit.movieexplorer.transfer.category.CategoryResponse;
import org.fasttrackit.movieexplorer.transfer.category.MovieInCategoryResponse;
import org.fasttrackit.movieexplorer.transfer.category.SaveCategoryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final MovieService movieService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, MovieService movieService) {
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

    public CategoryResponse createCategory(SaveCategoryRequest request) {
        LOGGER.info("Creating category {}", request);
        Category category = new Category();
        category.setGenre(request.getGenre());

        Category savedCategory = categoryRepository.save(category);

        return mapCategoryResponse(savedCategory);
    }

    /*GetCategory Service by id*/
//    @Transactional
//    public CategoryResponse getCategory(long id) {
//        LOGGER.info("Retrieving category {}", id);
//
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Category " + id + " not found."));
//
//        CategoryResponse categoryResponse = new CategoryResponse();
//        categoryResponse.setId(category.getId());
//        categoryResponse.setGenre(category.getGenre());
//
//        List<MovieInCategoryResponse> movieDtos = new ArrayList<>();
//
//        for (Movie movie : category.getMovies()) {
//            MovieInCategoryResponse movieResponse = new MovieInCategoryResponse();
//            movieResponse.setId(movie.getId());
//            movieResponse.setTitle(movie.getTitle());
//            movieResponse.setRate(movie.getRate());
//
//            movieDtos.add(movieResponse);
//        }
//        categoryResponse.setMovies(movieDtos);
//
//        return categoryResponse;
//    }

    @Transactional
    public CategoryResponse getMoviesByCategory(String genre, Pageable pageable) {
        LOGGER.info("Retrieving category {}", genre);

        Category category = categoryRepository.findByGenre(genre);

//        List<CategoryResponse> categoryDtos = new ArrayList<>();

        List<MovieInCategoryResponse> movieDtos = new ArrayList<>();
        for (Movie movie : category.getMovies()) {
            MovieInCategoryResponse movieInCategoryResponse = new MovieInCategoryResponse();
            movieInCategoryResponse.setId(movie.getId());
            movieInCategoryResponse.setTitle(movie.getTitle());
            movieInCategoryResponse.setRate(movie.getRate());
            movieInCategoryResponse.setPoster(movie.getPoster());
            movieInCategoryResponse.setCategories(mapCategories(movie.getCategories()));
            movieDtos.add(movieInCategoryResponse);
        }

        CategoryResponse categoryResponse = mapCategoryResponse(category);

        categoryResponse.setMovies(movieDtos);

        return categoryResponse;

    }

    private CategoryResponse mapCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setGenre(category.getGenre());

        return categoryResponse;
    }

    private List<String> mapCategories(Set<Category> categories) {
        return categories.stream()
                .map(category -> {
                    String firstLetter = category.getGenre().substring(0, 1).toUpperCase();
                    return firstLetter + category.getGenre().substring(1);
                })
                .collect(Collectors.toList());
    }

}


