package org.fasttrackit.movieexplorer.unitTests;

import org.fasttrackit.movieexplorer.domain.Category;
import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.persistence.CategoryRepository;
import org.fasttrackit.movieexplorer.service.CategoryService;
import org.fasttrackit.movieexplorer.service.MovieService;
import org.fasttrackit.movieexplorer.transfer.category.AddMovieToCategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceUnitTests {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryService(categoryRepository, movieService);
    }

    @Test
    public void addMovieToCategory_whenFindCategoryById_thenAddToMovie() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(categoryRepository.getOne(1L)));

        Category category = new Category();
        category.setId(1L);

        Movie movie = new Movie();
        movie.setId(2L);
        movie.setTitle("T");
        movie.setDescription("a");
        movie.setPoster(".jpg");
        movie.setTrailer(".avi");

        when(movieService.getMovie(anyLong())).thenReturn(movie);

        when(categoryRepository.save(any(Category.class))).thenReturn(null);


        AddMovieToCategoryRequest request = new AddMovieToCategoryRequest();
        request.setMovieIds(Collections.singletonList(movie.getId()));

        categoryService.addMovieToCategory(category.getId(), request);

        verify(categoryRepository).findById(anyLong());
        verify(movieService).getMovie(anyLong());
        verify(categoryRepository).save(any(Category.class));

    }


}
