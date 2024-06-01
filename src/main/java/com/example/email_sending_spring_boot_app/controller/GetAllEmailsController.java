package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.Email;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mail")
public class GetAllEmailsController {
    @Autowired
    EmailRepository emailRepository;

    @GetMapping("/emails/all")
    public ResponseEntity<List<Email>> getAllEmails() {
        try {
            List<Email> emails = emailRepository.findAll();
            return ResponseEntity.ok(emails);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
