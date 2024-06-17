package com.example.email_sending_spring_boot_app.impl;

import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import com.example.email_sending_spring_boot_app.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UsersService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
