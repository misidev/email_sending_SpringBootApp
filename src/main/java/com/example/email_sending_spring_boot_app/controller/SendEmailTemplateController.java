package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.model.response.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import com.lowagie.text.DocumentException;
import jakarta.annotation.PreDestroy;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;

@RestController
@RequestMapping("/api/v1/mail")
public class SendEmailTemplateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailTemplateController.class);

    public static final String[] EMAIL_LIST = new String[]{EMAIL, EMAIL_1};

    String partOfHtmlContent = null;

    @Autowired
    private EmailSenderService emailSenderService;

    private EmailTemplateResponse emailTemplateResponse = null;
    @Autowired
    private HttpServletResponse httpServletResponse;

    //    private EmailResponse emailResponse = null;
    private CompletableFuture<EmailResponse> emailResponse = null;

    //send email with template for Knowledge sharing
    @PostMapping("/sendEmailTemplate")
    public ResponseEntity<EmailTemplateResponse> sendEmail(@RequestBody EmailRequest emailRequest) throws IOException, MessagingException {
        emailSenderService.sendEmailsTemplate(emailRequest, TEMPLATE_EMAIL);
        emailTemplateResponse = handleSuccessResponseTemplate(emailRequest);

        return ResponseEntity.ok(emailTemplateResponse);
    }

    //Email one or more defined email addresses to notify that the application has started running
    @PostMapping("/sendAppStartEmail")
    @EventListener(ApplicationReadyEvent.class)
    public CompletableFuture<ResponseEntity<EmailResponse>> triggerMail() throws IOException, DocumentException, MessagingException {
        Date currentDateAndTime = new Date();

        emailResponse = emailSenderService.sendEmailsAppStartsShutdownAsyncWrapper(EMAIL_LIST,
                APP_STARTING_SUBJECT,
                APP_STARTING_STATUS,
                TEMPLATE_START_SHUTDOWN,
                currentDateAndTime,
                SIGNATURE);

        LOGGER.info(APP_STARTING);

        return emailResponse.thenApply(ResponseEntity::ok);
    }

    //Email one or more defined email addresses to notify that the application has started to shut down
    @PostMapping("/sendShutdownEmail")
    @PreDestroy
    public CompletableFuture<ResponseEntity<EmailResponse>> triggerMailOnShutdown() throws IOException, DocumentException, MessagingException {
        Date currentDateAndTime = new Date();

        emailResponse = emailSenderService.sendEmailsAppStartsShutdownAsyncWrapper(EMAIL_LIST,
                APP_SHUTDOWN_SUBJECT,
                APP_SHUTDOWN_STATUS,
                TEMPLATE_START_SHUTDOWN,
                currentDateAndTime,
                SIGNATURE);

        LOGGER.info(ApplicationConstants.APP_SHUTDOWN);
        return emailResponse.thenApply(ResponseEntity::ok);
    }

    public EmailTemplateResponse handleSuccessResponseTemplate(EmailRequest emailRequest) {
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

//    public static EmailTemplateResponse wrapSuccess(EmailRequest emailRequest) {
//        EmailRequest data = new EmailRequest(emailRequest.getToEmail(),
//                emailRequest.getSubject(),
//                emailRequest.getEventName(),
//                emailRequest.getEventDate(),
//                emailRequest.getEventTime(),
//                emailRequest.getEventLocation(),
//                emailRequest.getEventRegistrationLink(),
//                emailRequest.getRecipientName(),
//                emailRequest.getCompanyName(),
//                emailRequest.getYourName(),
//                emailRequest.getYourJobTitle(),
//                emailRequest.getSignature());
//
//        return new EmailTemplateResponse(
//                STATUS_SUCCESS,
//                HttpStatus.OK,
//                data,
//                LOGGER_MESSAGE_FOR_SIMPLE_MAIL
//        );
//    }
//
//    public EmailTemplateResponse handleSuccessResponseTemplate(EmailRequest emailRequest) {
//        EmailTemplateResponse emailTemplateResponse = wrapSuccess(emailRequest);
//        LOGGER.info("Email response for /sendEmail endpoint - 200 OK: {}", emailResponse);
//        return emailTemplateResponse;
//    }

}
