package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mail")
public class SendEmailController {
    private static EmailTemplateResponse emailTemplateResponse = null;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sendEmail")
    public EmailTemplateResponse sentEmail(@RequestParam(name = "user", required = true) String user,
                                           @RequestParam(name = "subject", required = true) String subject,
                                           @RequestParam(name = "body", required = true) String body,
                                           @RequestBody(required = false) MultipartFile attachments) {

        emailSenderService.sendAttachedEmailThroughRequest(new String[]{user}, subject, body, attachments);

        emailTemplateResponse = handleSuccessResponseAttachment(user, subject, body, attachments);

        return emailTemplateResponse;
    }

    public static EmailTemplateResponse handleSuccessResponseAttachment(String user, String subject, String body, MultipartFile attachments) {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(new String[]{user},
                subject,
                body,
                attachments.getOriginalFilename());

        emailTemplateResponse = new EmailTemplateResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        return emailTemplateResponse;
    }
}
