package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.EmailTemplate;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/mail")
public class SendMailWithAttachmentController {
    @Autowired
    private EmailSenderService emailSenderService;
    private static final Logger logger = LoggerFactory.getLogger(SendMailWithAttachmentController.class);
    private static final String body = "Get request for user with attachment is triggered!";
    private static final String subject = "App is started";
    private static final String file = "src/main/resources/images/test.jpg";

    @PostMapping("/sendEmailWithAttachment")
    public EmailTemplate sentEmailWithAttachment(@RequestParam(name = "user", required = true) String user) throws MessagingException, IOException {

        emailSenderService.sendAttachedEmail(new String[]{user}, subject, body,file);
        EmailTemplate emailTemplate = new EmailTemplate(new String[]{user}, subject, body);

        logger.info("Email to notify that the application has started running GET REQUEST email with attachment.");
        return emailTemplate;
    }
}
