package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mail")
public class GetAllEmailsController {
    private static final Logger logger = LoggerFactory.getLogger(GetAllEmailsController.class);

    @Autowired
    EmailRepository emailRepository;

    @GetMapping("/emails/all")
    public ResponseEntity<List<Email>> getAllEmails() {
        try {
            List<Email> emails = emailRepository.findAll();

            logger.info(ApplicationConstants.LOGGER_MESSAGE_GET_ALL_EMAILS);
            return ResponseEntity.ok(emails);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
