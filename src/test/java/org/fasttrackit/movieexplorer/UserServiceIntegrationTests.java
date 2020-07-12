package org.fasttrackit.movieexplorer;

import org.fasttrackit.movieexplorer.domain.User;
import org.fasttrackit.movieexplorer.service.UserService;
import org.fasttrackit.movieexplorer.transfer.user.SaveUserRequest;
import org.fasttrackit.movieexplorer.domain.User;
import org.fasttrackit.movieexplorer.transfer.user.SaveUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    void createUser_whenValidRequest_thenReturnCreatedUser() {
        createUser();
    }

    private User createUser() {
        SaveUserRequest request = new SaveUserRequest();
        request.setFirstName("Titanic");
        request.setLastName("Is the most awarded user of all times.");
        request.setEmail("TitanicIMG");

        User user = userService.createUser (request);

        assertThat(user, notNullValue());
        assertThat(user.getId(), greaterThan(0L));
        assertThat(user.getFirstName(), is(request.getFirstName()));
        assertThat(user.getLastName(), is(request.getLastName()));
        assertThat(user.getEmail(), is(request.getEmail()));

        return user;
    }

}
