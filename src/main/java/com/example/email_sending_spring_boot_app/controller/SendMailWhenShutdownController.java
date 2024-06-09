package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import com.lowagie.text.DocumentException;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;

//Email one or more defined email addresses to notify that the application has started to shut down
@RestController
@RequestMapping("/api/mail")
public class SendMailWhenShutdownController {
    private static final Logger logger = LoggerFactory.getLogger(SendMailWhenShutdownController.class);

    protected static final String[] EMAIL_LIST = new String[]{"milicasimovic77@yahoo.com"};
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendShutdownEmail")
    @PreDestroy
    public ResponseEntity<EmailResponse> triggerMailOnShutdown() throws IOException, DocumentException {
        Date currentDateAndTime = new Date();

        EmailResponse emailResponse = emailSenderService.sendEmailsAppStartsShutdown(EMAIL_LIST,
                APP_SHUTDOWN_SUBJECT,
                APP_SHUTDOWN_STATUS,
                TEMPLATE_START_SHUTDOWN,
                currentDateAndTime,
                SIGNATURE);

        logger.info(ApplicationConstants.APP_SHUTDOWN);
        return ResponseEntity.ok(emailResponse);
    }

}
