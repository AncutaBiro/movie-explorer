package org.fasttrackit.movieexplorer;

import org.fasttrackit.movieexplorer.domain.Movie;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.service.MovieService;
import org.fasttrackit.movieexplorer.transfer.GetMoviesRequest;
import org.fasttrackit.movieexplorer.transfer.SaveMovieRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class MovieServiceIntegrationTests {

    @Autowired
    private MovieService movieService;

    @Test
    void createMovie_whenValidRequest_thenReturnCreatedMovie() {
        SaveMovieRequest request = new SaveMovieRequest();
        request.setTitle("Titanic");
        request.setDescription("Is the most awarded movie of all times.");
        request.setPoster("TitanicIMG");
        request.setTrailer("TitanicAVI");
        request.setGenre("Drama");
        request.setLeadingRole("Leonardo Di Caprio");
        request.setRate(BigDecimal.valueOf(5.5));

        Movie movie = movieService.createMovie(request);
        assertThat(movie, notNullValue());
        assertThat(movie.getId(), greaterThan(0L));
        assertThat(movie.getTitle(), is(request.getTitle()));
        assertThat(movie.getDescription(), is(request.getDescription()));
        assertThat(movie.getPoster(), is(request.getPoster()));
        assertThat(movie.getTrailer(), is(request.getTrailer()));
        assertThat(movie.getGenre(), is(request.getGenre()));
        assertThat(movie.getLeadingRole(), is(request.getLeadingRole()));
        assertThat(movie.getRate(), is(request.getRate()));
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
        request.setDescription("Is the most awarded movie of all times.");
        request.setPoster("TitanicIMG");
        request.setTrailer("TitanicAVI");
        request.setGenre("Drama");
        request.setRate(BigDecimal.valueOf(5.3));

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
        assertThat(response.getTitle(), is(movie.getTitle()));
        assertThat(response.getDescription(), is(movie.getDescription()));
        assertThat(response.getPoster(), is(movie.getPoster()));
        assertThat(response.getTrailer(), is(movie.getTrailer()));
        assertThat(response.getGenre(), is(movie.getGenre()));
        assertThat(response.getLeadingRole(), is(movie.getLeadingRole()));
        assertThat(response.getRate(), is(movie.getRate()));
    }

    // nu stiu cum sa implementez asserturile???
//    @Test
//      void getMoviesByCriteria_whenExistingMovie_thenReturnMovieList () {
//
//        Movie movie = createMovie();
//
//        GetMoviesRequest request = new GetMoviesRequest();
//        request.setPartialTitle("Tit");
//        request.setFindGenre("drama");
//        request.setFindLeadingRole("Leonardo Di Caprio");
//
//        Page<Movie> response = movieService.getMoviesBy(request, PageRequest.of(0,1000));
//
//        assertThat(response, notNullValue());
//        assertThat(response.getTotalElements(), greaterThanOrEqualTo(1L));
////        assertThat(response. , is(movie.getGenre()));
//    }


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
        BigDecimal bd2 = new BigDecimal("3.0");

        SaveMovieRequest request = new SaveMovieRequest();
        request.setTitle(movie.getTitle() + "Updated");
        request.setDescription(movie.getDescription() + "Updated");
        request.setTrailer(movie.getTrailer() + "Updated");
        request.setPoster(movie.getPoster() + "Updated");
        request.setGenre(movie.getGenre() + "Updated");
        request.setLeadingRole(movie.getLeadingRole() + "Updated");
        request.setRate(movie.getRate().add(bd2));

        Movie updatedMovie = movieService.updateMovie(movie.getId(), request);

        assertThat(updatedMovie, notNullValue());
        assertThat(updatedMovie.getId(), is(movie.getId()));
        assertThat(updatedMovie.getTitle(), is(request.getTitle()));
        assertThat(updatedMovie.getDescription(), is(request.getDescription()));
        assertThat(updatedMovie.getTrailer(), is(request.getTrailer()));
        assertThat(updatedMovie.getPoster(), is(request.getPoster()));
        assertThat(updatedMovie.getGenre(), is(request.getGenre()));
        assertThat(updatedMovie.getLeadingRole(), is(request.getLeadingRole()));
        assertThat(updatedMovie.getRate(), is(request.getRate()));
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
        Movie movie = createMovie();

        movieService.deleteMovie(movie.getId());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> movieService.getMovie(movie.getId()));
    }


    @Test
// test failed:
// org.opentest4j.AssertionFailedError: Unexpected exception type thrown ==>
// expected: <org.fasttrackit.movieexplorer.exception.ResourceNotFoundException>
// but was: <org.springframework.dao.EmptyResultDataAccessException>
    void deleteMovie_whenNonExistingMovie_thenThrowException() {

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> movieService.deleteMovie(0));

//        try {
//            movieService.deleteMovie(0);
//        } catch (Exception e) {
//            assertThat("Unexpected exception", e instanceof ResourceNotFoundException);
//        }

    }

    private Movie createMovie() {
        SaveMovieRequest request = new SaveMovieRequest();
        request.setTitle("Titanic");
        request.setDescription("Is the most awarded movie of all times.");
        request.setPoster("TitanicIMG");
        request.setTrailer("TitanicAVI");
        request.setGenre("Drama");
        request.setLeadingRole("Leonardo di Caprio");
        request.setRate(BigDecimal.valueOf(5.5));

        Movie movie = movieService.createMovie(request);
        assertThat(movie, notNullValue());
        assertThat(movie.getId(), greaterThan(0L));
        assertThat(movie.getTitle(), is(request.getTitle()));
        assertThat(movie.getDescription(), is(request.getDescription()));
        assertThat(movie.getPoster(), is(request.getPoster()));
        assertThat(movie.getTrailer(), is(request.getTrailer()));
        assertThat(movie.getGenre(), is(request.getGenre()));
        assertThat(movie.getLeadingRole(), is(request.getLeadingRole()));
        assertThat(movie.getRate(), is(request.getRate()));

        return movie;
    }

}
