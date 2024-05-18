package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mail")
public class SendEmail {
    private EmailTemplateResponse emailTemplateResponse = null;

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

        emailTemplateResponse = SendMailWithAttachmentController.handleSuccessResponseAttachment(user);

        return emailTemplateResponse;
    }
}
