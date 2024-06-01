package com.example.email_sending_spring_boot_app.repository;

import com.example.email_sending_spring_boot_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByUsername(String username);
}
