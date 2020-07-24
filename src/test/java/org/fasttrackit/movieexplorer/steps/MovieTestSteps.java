//package org.fasttrackit.movieexplorer.steps;
//
//import org.fasttrackit.movieexplorer.domain.Movie;
//import org.fasttrackit.movieexplorer.service.MovieService;
//import org.fasttrackit.movieexplorer.transfer.movie.SaveMovieRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.greaterThan;
//
//@Component
//public class MovieTestSteps {
//
//    @Autowired
//    private MovieService movieService;
//
//
//    public Movie createMovie() {
//        SaveMovieRequest request = new SaveMovieRequest();
//        request.setTitle("Titanic");
//        request.setDescription("Is the most awarded movie of all times.");
//        request.setPoster("TitanicIMG");
//        request.setTrailer("TitanicAVI");
//        request.setGenre("Drama");
//        request.setLeadingRole("Leonardo di Caprio");
//        request.setRate(BigDecimal.valueOf(5.5));
//
//        Movie movie = movieService.createMovie(request);
//        assertThat(movie, notNullValue());
//        assertThat(movie.getId(), greaterThan(0L));
//        assertThat(movie.getTitle(), is(request.getTitle()));
//        assertThat(movie.getDescription(), is(request.getDescription()));
//        assertThat(movie.getPoster(), is(request.getPoster()));
//        assertThat(movie.getTrailer(), is(request.getTrailer()));
//        assertThat(movie.getGenre(), is(request.getGenre()));
//        assertThat(movie.getLeadingRole(), is(request.getLeadingRole()));
//        assertThat(movie.getRate(), is(request.getRate()));
//
//        return movie;
//    }
//}
