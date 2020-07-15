package org.fasttrackit.movieexplorer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.movieexplorer.domain.User;
import org.fasttrackit.movieexplorer.exception.ResourceNotFoundException;
import org.fasttrackit.movieexplorer.persistence.UserRepository;
import org.fasttrackit.movieexplorer.transfer.user.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    public User createUser (SaveUserRequest request) {
        LOGGER.info("Creating user {}", request);

//        User user = new User();
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setEmail(request.getEmail());

        User user = objectMapper.convertValue(request, User.class);

        return userRepository.save(user);
    }

    public User getUser (long id) {
        LOGGER.info("Retrieving user {}", id);

//        Optional<User> user = userRepository.findById(id);
//        if (user.isPresent()) {
//            return user.get();
//        } else throw new ResourceNotFoundException("User " + id + " not found.");

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User" + id + "not found."));
    }









}
