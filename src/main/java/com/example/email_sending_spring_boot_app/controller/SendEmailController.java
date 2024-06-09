package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;

@RestController
@RequestMapping("/api/mail")
public class SendEmailController {
    private EmailResponse emailResponse = null;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sendEmail")
    public ResponseEntity<EmailResponse> sentEmail(@RequestParam(name = USER, required = true) String user,
                                                   @RequestParam(name = SUBJECT, required = true) String subject,
                                                   @RequestParam(name = BODY, required = true) String body,
                                                   @RequestBody(required = false) MultipartFile attachments) {

        emailResponse = emailSenderService.sendAttachedEmailThroughRequest(new String[]{user}, subject, body, attachments);

        return ResponseEntity.ok(emailResponse);
    }

}
