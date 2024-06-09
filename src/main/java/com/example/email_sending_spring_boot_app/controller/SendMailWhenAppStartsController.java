package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;

//Email one or more defined email addresses to notify that the application has started running
@RestController
@RequestMapping("/api/mail")
public class SendMailWhenAppStartsController {
    private static final Logger logger = LoggerFactory.getLogger(SendMailWhenAppStartsController.class);

    protected static final String[] EMAIL_LIST = new String[]{"milicasimovic77@yahoo.com"};
    @Autowired
    private EmailSenderService senderService;


    String partOfHtmlContent = null;

    @PostMapping("/sendAppStartEmail")
    @EventListener(ApplicationReadyEvent.class)
    public ResponseEntity<EmailResponse> triggerMail() throws IOException, DocumentException {
        Date currentDateAndTime = new Date();

        EmailResponse emailResponse = senderService.sendEmailsAppStartsShutdown(EMAIL_LIST,
                APP_STARTING_SUBJECT,
                APP_STARTING_STATUS,
                TEMPLATE_START_SHUTDOWN,
                currentDateAndTime,
                SIGNATURE);

        logger.info(APP_STARTING);

        return ResponseEntity.ok(emailResponse);
    }

}
