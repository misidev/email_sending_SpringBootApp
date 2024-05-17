package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.model.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}