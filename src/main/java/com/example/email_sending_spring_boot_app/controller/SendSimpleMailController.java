package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class SendSimpleMailController {
    private static final Logger logger = LoggerFactory.getLogger(SendSimpleMailController.class);

    private EmailTemplateResponse emailTemplateResponse = null;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendSimpleEmail")
    public EmailTemplateResponse sentEmail(@RequestParam(name = "user", required = true) String user) {
        emailSenderService.sendSimpleEmail(new String[]{user},
                ApplicationConstants.SUBJECT_FOR_SIMPLE_MAIL,
                ApplicationConstants.BODY_FOR_SIMPLE_MAIL);

         emailTemplateResponse = handleSuccessResponseSimple(user);

        logger.info(ApplicationConstants.LOGGER_MESSAGE_FOR_SIMPLE_MAIL);
        return emailTemplateResponse;
    }

    private EmailTemplateResponse handleSuccessResponseSimple(String user) {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(new String[]{user},
                ApplicationConstants.SUBJECT_FOR_SIMPLE_MAIL,
                ApplicationConstants.BODY_FOR_SIMPLE_MAIL);

         emailTemplateResponse = new EmailTemplateResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.LOGGER_MESSAGE_FOR_SIMPLE_MAIL);

        return emailTemplateResponse;
    }


}
