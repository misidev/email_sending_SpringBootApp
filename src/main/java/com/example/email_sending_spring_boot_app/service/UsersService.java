package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.model.entity.User;

import java.util.List;

public interface UsersService {
    List<User> findAllUsers();

    User getUserByEmail(String email);
}
