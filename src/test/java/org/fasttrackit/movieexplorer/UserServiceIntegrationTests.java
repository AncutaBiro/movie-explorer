package org.fasttrackit.movieexplorer;

import org.fasttrackit.movieexplorer.domain.User;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.service.UserService;
import org.fasttrackit.movieexplorer.transfer.user.SaveUserRequest;
import org.fasttrackit.movieexplorer.domain.User;
import org.fasttrackit.movieexplorer.transfer.user.SaveUserRequest;
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
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    void createUser_whenValidRequest_thenReturnCreatedUser() {
        createUser();
    }

    @Test
    void createUser_whenMissingAllMandatoryProperties_ThenThrowException () {
        SaveUserRequest request = new SaveUserRequest();

        try {
            userService.createUser(request);
        } catch (Exception e) {
            assertThat(("Unexpected exception"), e instanceof ConstraintViolationException);
        }
    }

    @Test
    void getUser_whenExistingUser_thenDisplayUser() {
        User user = createUser();

        User response = userService.getUser(user.getId());

        assertThat(response, notNullValue());
        assertThat(response.getFirstName(), is(user.getFirstName()));
        assertThat(response.getLastName(), is(user.getLastName()));
        assertThat(response.getEmail(), is(user.getEmail()));
    }

    @Test
    void getUser_whenNotExistingUser_thenThrowException() {
        try {
            userService.getUser(0L);
        } catch (Exception e) {
            assertThat("Unexpected exception", e instanceof ResourceNotFoundException);
        }
    }

    private User createUser() {
        SaveUserRequest request = new SaveUserRequest();
        request.setFirstName("Tom");
        request.setLastName("Dom");
        request.setEmail("t.d@yahoo.com");

        User user = userService.createUser (request);

        assertThat(user, notNullValue());
        assertThat(user.getId(), greaterThan(0L));
        assertThat(user.getFirstName(), is(request.getFirstName()));
        assertThat(user.getLastName(), is(request.getLastName()));
        assertThat(user.getEmail(), is(request.getEmail()));

        return user;
    }
}