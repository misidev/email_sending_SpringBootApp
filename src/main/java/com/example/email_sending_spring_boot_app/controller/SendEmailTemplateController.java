package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.model.response.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;

@Controller
@RequestMapping("/api/mail")
public class SendEmailTemplateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailTemplateController.class);

    @Autowired
    private EmailSenderService emailSenderService;

    private EmailTemplateResponse emailTemplateResponse = null;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @PostMapping("/send-email-template")
    public ResponseEntity<EmailTemplateResponse> sendEmail(@RequestBody EmailRequest emailRequest) throws IOException {
        emailSenderService.sendEmailsTemplate(emailRequest, TEMPLATE_EMAIL);
        emailTemplateResponse = handleSuccessResponseTemplate(emailRequest);

        return ResponseEntity.ok(emailTemplateResponse);
    }

    public EmailTemplateResponse handleSuccessResponseTemplate(EmailRequest emailRequest) {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        EmailRequest data = new EmailRequest(emailRequest.getToEmail(),
                emailRequest.getSubject(),
                emailRequest.getEventName(),
                emailRequest.getEventDate(),
                emailRequest.getEventTime(),
                emailRequest.getEventLocation(),
                emailRequest.getEventRegistrationLink(),
                emailRequest.getRecipientName(),
                emailRequest.getCompanyName(),
                emailRequest.getYourName(),
                emailRequest.getYourJobTitle(),
                emailRequest.getSignature());

        emailTemplateResponse = new EmailTemplateResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                data,
                LOGGER_MESSAGE_FOR_SIMPLE_MAIL);

        LOGGER.info("Email response for /sendEmail endpoint - 200 OK: {}", emailTemplateResponse);
        return emailTemplateResponse;
    }

}
