package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.model.response.UserResponse;
import com.example.email_sending_spring_boot_app.model.response.UsersResponse;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import com.example.email_sending_spring_boot_app.util.HandleDbInputAndResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.LOGGER_MESSAGE_ADD_USER;
import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.LOGGER_MESSAGE_GET_ALL_USERS;

@RestController
@RequestMapping("/api/v1/mail/users")
public class UsersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HandleDbInputAndResponses handleDbInputAndResponses;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        LOGGER.info(LOGGER_MESSAGE_ADD_USER);
        return ResponseEntity.ok(handleDbInputAndResponses.handleSuccessResponseAddUser(savedUser));

    }

    @GetMapping("/all")
    public ResponseEntity<UsersResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        LOGGER.info(LOGGER_MESSAGE_GET_ALL_USERS);
        return ResponseEntity.ok(handleDbInputAndResponses.handleSuccessResponseGetUsers(users));
    }

}
