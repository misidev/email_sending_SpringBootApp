package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mail")
public class GetAllUsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
