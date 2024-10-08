package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;

@RestController
@RequestMapping("/api/v1/mail")
public class SendEmailWithAttachmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailWithAttachmentController.class);

    private CompletableFuture<EmailResponse> emailResponse = null;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private JavaMailSender javaMailSender;

    //MultipartFile attachments
    @PostMapping("/sendEmail")
    public CompletableFuture<ResponseEntity<EmailResponse>> sentEmail(@RequestParam(name = USER, required = true) String user,
                                                                      @RequestParam(name = SUBJECT, required = true) String subject,
                                                                      @RequestParam(name = BODY, required = true) String body,
                                                                      @RequestBody(required = true) MultipartFile attachments) throws MessagingException, IOException {

        emailResponse = emailSenderService.sendAttachedEmailThroughRequestAsyncWrapper(new String[]{user}, subject, body, attachments);

        return emailResponse.thenApply(ResponseEntity::ok);
    }

    //send attachment from path
    @PostMapping("/sendEmailWithAttachment")
    public CompletableFuture<ResponseEntity<EmailResponse>> sentEmailWithAttachment(@RequestParam(name = USER, required = true) String user,
                                                                                    @RequestParam(name = SUBJECT, required = true) String subject,
                                                                                    @RequestParam(name = BODY, required = true) String body,
                                                                                    @RequestParam(name = FILE, required = true) String file) throws MessagingException, IOException {

        emailResponse = emailSenderService.sendEmailWithAttachmentAsyncWrapper(user, subject, body, file, null, null);

        LOGGER.info(LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);
        return emailResponse.thenApply(ResponseEntity::ok);
    }

}
