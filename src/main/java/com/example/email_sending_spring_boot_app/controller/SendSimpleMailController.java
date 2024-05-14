package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.EmailTemplate;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class SendSimpleMailController {
    public static final String SUBJECT = "User notification";
    public static final String BODY = "Get request for user is triggered!";
    private static final Logger logger = LoggerFactory.getLogger(SendSimpleMailController.class);
    private static final String LOGGER_MESSAGE = "Email to notify that the application has started running GET REQUEST simple email.";

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendSimpleEmail")
    public EmailTemplate sentEmail(@RequestParam(name = "user", required = true) String user) {
        emailSenderService.sendSimpleEmail(new String[]{user}, SUBJECT, BODY);
        EmailTemplate emailTemplate = new EmailTemplate(new String[]{user}, SUBJECT, BODY);

        logger.info(LOGGER_MESSAGE);
        return emailTemplate;
    }

}
