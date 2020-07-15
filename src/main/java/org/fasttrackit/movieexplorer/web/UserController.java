package org.fasttrackit.movieexplorer.web;

import org.fasttrackit.movieexplorer.domain.User;
import org.fasttrackit.movieexplorer.service.UserService;
import org.fasttrackit.movieexplorer.transfer.user.SaveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping ("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity <User> createUser (@RequestBody @Valid SaveUserRequest request) {
        User user = userService.createUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<User> getUser (@PathVariable long id) {
       User user = userService.getUser(id);
       return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
