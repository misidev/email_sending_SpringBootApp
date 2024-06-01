package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Email one or more defined email addresses to notify that the application has started running
@RestController
@RequestMapping("/api/mail")
public class SendMailWhenAppStartsController {
    private static final Logger logger = LoggerFactory.getLogger(SendMailWhenAppStartsController.class);
    private EmailTemplateResponse emailTemplateResponse = null;

    protected static final String[] EMAIL_LIST = new String[]{"milicasimovic77@yahoo.com"};
    @Autowired
    private EmailSenderService senderService;

    @PostMapping("/sendAppStartEmail")
    @EventListener(ApplicationReadyEvent.class)
    public EmailTemplateResponse triggerMail() {
        senderService.sendSimpleEmail(EMAIL_LIST,
                ApplicationConstants.APP_STARTING_SUBJECT,
                ApplicationConstants.APP_STARTING_BODY);

        emailTemplateResponse = handleSuccessResponseAppStarts();

        logger.info(ApplicationConstants.APP_STARTING);

        return emailTemplateResponse;
    }

    private EmailTemplateResponse handleSuccessResponseAppStarts() {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(EMAIL_LIST,
                ApplicationConstants.APP_STARTING_SUBJECT,
                ApplicationConstants.APP_STARTING_BODY);

        emailTemplateResponse = new EmailTemplateResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.APP_STARTING);

        return emailTemplateResponse;
    }

}
