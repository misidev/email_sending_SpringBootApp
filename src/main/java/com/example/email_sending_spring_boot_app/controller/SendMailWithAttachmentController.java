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
    private static final Logger logger = LoggerFactory.getLogger(SendMailWithAttachmentController.class);
    public static final String SUBJECT = "User notification with attachment";
    public static final String BODY = "Get request for user with attachment is triggered!";
    public static final String FILE = "src/main/resources/images/test.jpg";
    private static final String LOGGER_MESSAGE = "Email to notify that the application has started running GET REQUEST email with attachment.";

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendEmailWithAttachment")
    public EmailTemplate sentEmailWithAttachment(@RequestParam(name = "user", required = true) String user) throws MessagingException, IOException {

        emailSenderService.sendAttachedEmail(new String[]{user}, SUBJECT, BODY, FILE);
        EmailTemplate emailTemplate = new EmailTemplate(new String[]{user}, SUBJECT, BODY);

        logger.info(LOGGER_MESSAGE);
        return emailTemplate;
    }

}
