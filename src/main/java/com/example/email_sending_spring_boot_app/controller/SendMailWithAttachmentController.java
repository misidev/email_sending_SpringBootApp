package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
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
public class SendMailWithAttachmentController {
    private static final Logger logger = LoggerFactory.getLogger(SendMailWithAttachmentController.class);
    @Autowired
    private EmailSenderService emailSenderService;

    //for one user
    @PostMapping("/sendEmailWithAttachment")
    public ResponseEntity<EmailResponse> sentEmailWithAttachment(@RequestParam(name = USER, required = true) String user,
                                                                @RequestParam(name = SUBJECT, required = true) String subject,
                                                                @RequestParam(name = BODY, required = true) String body,
                                                                @RequestParam(name = FILE, required = true) String file) {

        EmailResponse emailResponse = emailSenderService.sendAttachedEmail(user, subject, body, file, null, null);

        logger.info(LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);
        return ResponseEntity.ok(emailResponse);
    }

}
