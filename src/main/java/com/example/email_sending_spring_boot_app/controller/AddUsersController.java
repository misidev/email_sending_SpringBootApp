package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class AddUsersController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            User savedUser = userRepository.save(user);

            return ResponseEntity.ok("User added successfully with ID: " + savedUser.getId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding user");
        }
    }

}
