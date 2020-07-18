package org.fasttrackit.movieexplorer.steps;

import org.fasttrackit.movieexplorer.domain.User;
import org.fasttrackit.movieexplorer.service.UserService;
import org.fasttrackit.movieexplorer.transfer.user.SaveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class UserTestSteps {

    @Autowired
    private UserService userService;

    public User createUser() {
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
