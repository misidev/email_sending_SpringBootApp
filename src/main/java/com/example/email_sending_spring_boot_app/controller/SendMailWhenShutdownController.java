package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Email one or more defined email addresses to notify that the application has started to shut down
@RestController
@RequestMapping("/api/mail")
public class SendMailWhenShutdownController {
    private static final Logger logger = LoggerFactory.getLogger(SendMailWhenShutdownController.class);
    private EmailTemplateResponse emailTemplateResponse = null;

    protected static final String[] EMAIL_LIST = new String[]{"milicasimovic77@yahoo.com"};
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendShutdownEmail")
    @PreDestroy
    public EmailTemplateResponse triggerMailOnShutdown() {
        emailSenderService.sendSimpleEmail(EMAIL_LIST,
                ApplicationConstants.APP_SHUTDOWN_SUBJECT,
                ApplicationConstants.APP_SHUTDOWN_BODY);

        emailTemplateResponse = handleSuccessResponseShutdown();

        logger.info(ApplicationConstants.APP_SHUTDOWN);
        return emailTemplateResponse;
    }

    private EmailTemplateResponse handleSuccessResponseShutdown() {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(EMAIL_LIST,
                ApplicationConstants.APP_SHUTDOWN_SUBJECT,
                ApplicationConstants.APP_SHUTDOWN_BODY);

        emailTemplateResponse = new EmailTemplateResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.APP_SHUTDOWN);

        return emailTemplateResponse;
    }

}
