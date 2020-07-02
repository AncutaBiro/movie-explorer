package org.fasttrackit.movieexplorer;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.service.MovieService;
import org.fasttrackit.movieexplorer.transfer.SaveMovieRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
class MovieServiceIntegrationTests {

    @Autowired
    private MovieService movieService;

    @Test
    void createMovie_whenValidRequest_thenReturnCreatedMovie() {
        SaveMovieRequest request = new SaveMovieRequest();
        request.setName("Titanic");
        request.setDescription("Is the most awarded movie of all times.");
        request.setPoster("TitanicIMG");
        request.setTrailer("TitanicAVI");

        Movie movie = movieService.createMovie(request);
        assertThat(movie, notNullValue());
        assertThat(movie.getId(), greaterThan(0L));
        assertThat(movie.getName(), is(request.getName()));
        assertThat(movie.getDescription(), is(request.getDescription()));
        assertThat(movie.getPoster(), is(request.getPoster()));
        assertThat(movie.getTrailer(), is(request.getTrailer()));

//        function for creating movie to call instead of the code above
//        createMovie();

    }

    @Test
    void createMovie_whenMissingMandatoryProperties_thenThrowException() {
        SaveMovieRequest request = new SaveMovieRequest();

        try {
            movieService.createMovie(request);
        } catch (Exception e) {
            assertThat(("Unexpected exception"), e instanceof ConstraintViolationException);
            assertThat(((ConstraintViolationException) e).getConstraintViolations(), hasSize(3));
            //type casting for ConstrainsViolationException to e
        }
    }

    @Test
    void createMovie_whenMissingNameProperty_thenThrowException() {
        SaveMovieRequest request = new SaveMovieRequest();
        request.setName(null);
        request.setDescription("Is the most awarded movie of all times.");
        request.setPoster("TitanicIMG");
        request.setTrailer("TitanicAVI");

        try {
            movieService.createMovie(request);
        } catch (Exception e) {
            assertThat(("Unexpected exception"), e instanceof ConstraintViolationException);
        }
    }

    @Test
    void getMovie_whenExistingMovie_thenReturnMovie() {

        Movie movie = createMovie();

        Movie response = movieService.getMovie(movie.getId());
        assertThat(response, notNullValue());
        assertThat(response.getId(), is(movie.getId()));
        assertThat(response.getName(), is(movie.getName()));
        assertThat(response.getDescription(), is(movie.getDescription()));
        assertThat(response.getPoster(), is(movie.getPoster()));
        assertThat(response.getTrailer(), is(movie.getTrailer()));

    }

    @Test
    void getMovie_whenNonExistingMovie_thenThrowException() {
        try {
            movieService.getMovie(0);
        } catch (Exception e) {
            assertThat("Unexpected exception", e instanceof ResourceNotFoundException);
        }
    }

    @Test
    void updateMovie_whenValidRequest_thenReturnUpdatedMovie() {
        Movie movie = createMovie();

        SaveMovieRequest request = new SaveMovieRequest();
        request.setName(movie.getName() + "Updated");
        request.setDescription(movie.getDescription() + "Updated");
        request.setTrailer(movie.getTrailer() + "Updated");
        request.setPoster(movie.getPoster() + "Updated");

        Movie updatedMovie = movieService.updateMovie(movie.getId(), request);

        assertThat(updatedMovie, notNullValue());
        assertThat(updatedMovie.getId(), is(movie.getId()));
        assertThat(updatedMovie.getName(), is(request.getName()));
        assertThat(updatedMovie.getDescription(), is(request.getDescription()));
        assertThat(updatedMovie.getTrailer(), is(request.getTrailer()));
        assertThat(updatedMovie.getPoster(), is(request.getPoster()));
    }

    @Test
    void updateMovie_whenNonExistingMovie_thenThrowException() {
        SaveMovieRequest request = new SaveMovieRequest();

        try {
            movieService.updateMovie(0, request);
        } catch (Exception e) {
            assertThat("Unexpected exception", e instanceof ResourceNotFoundException);
        }
    }

    @Test
    void deleteMovie_whenValidRequest_thenMovieDoesNotExist() {
        Movie movie =  createMovie();

        movieService.deleteMovie(movie.getId());

        Assertions.assertThrows(ResourceNotFoundException.class,
                ()-> movieService.getMovie(movie.getId()));
    }

//    @Test
//    void deleteMovie_whenNonExistingMovie_thenThrowException() {
//
//        Assertions.assertThrows(ResourceNotFoundException.class,
//                ()-> movieService.deleteMovie(-1));
//
////        try {
////            movieService.deleteMovie(0);
////        } catch (Exception e) {
////            assertThat("Unexpected exception", e instanceof ResourceNotFoundException);
////        }
//
//    }

    private Movie createMovie() {
        SaveMovieRequest request = new SaveMovieRequest();
        request.setName("Titanic");
        request.setDescription("Is the most awarded movie of all times.");
        request.setPoster("TitanicIMG");
        request.setTrailer("TitanicAVI");

        Movie movie = movieService.createMovie(request);
        assertThat(movie, notNullValue());
        assertThat(movie.getId(), greaterThan(0L));
        assertThat(movie.getName(), is(request.getName()));
        assertThat(movie.getDescription(), is(request.getDescription()));
        assertThat(movie.getPoster(), is(request.getPoster()));
        assertThat(movie.getTrailer(), is(request.getTrailer()));

        return movie;
    }

}