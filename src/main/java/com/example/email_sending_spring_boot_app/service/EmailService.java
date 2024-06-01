package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.model.Email;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }
}