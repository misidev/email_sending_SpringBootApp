package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/mail")
public class SendMailWithAttachmentController {
    private static final Logger logger = LoggerFactory.getLogger(SendMailWithAttachmentController.class);
    private static EmailTemplateResponse emailTemplateResponse = null;
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendEmailWithAttachment")
    public EmailTemplateResponse sentEmailWithAttachment(@RequestParam(name = "user", required = true) String user) {
        emailSenderService.sendAttachedEmail(new String[]{user},
                ApplicationConstants.SUBJECT_FOR_MAIL_WITH_ATTACHMENT,
                ApplicationConstants.BODY_FOR_MAIL_WITH_ATTACHMENT,
                ApplicationConstants.FILE_FOR_MAIL_WITH_ATTACHMENT,null);

        emailTemplateResponse = handleSuccessResponseAttachment(user);

        logger.info(ApplicationConstants.LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);
        return emailTemplateResponse;
    }

    public static EmailTemplateResponse handleSuccessResponseAttachment(String user) {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(new String[]{user},
                ApplicationConstants.SUBJECT_FOR_MAIL_WITH_ATTACHMENT,
                ApplicationConstants.BODY_FOR_MAIL_WITH_ATTACHMENT,
                ApplicationConstants.FILE_NAME);

        emailTemplateResponse = new EmailTemplateResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        return emailTemplateResponse;
    }

}
