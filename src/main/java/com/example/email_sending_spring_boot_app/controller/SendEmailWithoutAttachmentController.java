package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.model.response.ErrorResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;

@RestController
@RequestMapping("/api/mail")
public class SendEmailWithoutAttachmentController {
    private static final Logger logger = LoggerFactory.getLogger(SendEmailWithoutAttachmentController.class);

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendEmailWithoutAttachment")
    public ResponseEntity<EmailResponse> sentEmail(@RequestParam(name = USER, required = true) String user) {
        EmailResponse emailResponse = emailSenderService.sendEmailsWithoutAttachment(new String[]{user},
                SUBJECT_FOR_SIMPLE_MAIL,
                BODY_FOR_SIMPLE_MAIL);

        logger.info(LOGGER_MESSAGE_FOR_SIMPLE_MAIL);
        return ResponseEntity.ok(emailResponse);
    }

}
