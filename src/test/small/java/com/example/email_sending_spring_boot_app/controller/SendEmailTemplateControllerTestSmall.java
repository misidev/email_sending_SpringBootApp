package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.model.response.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import com.example.email_sending_spring_boot_app.util.HandleDbInputAndResponses;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
class SendEmailTemplateControllerTestSmall {
    @Mock
    private EmailSenderService emailSenderService;
    @Mock
    private HandleDbInputAndResponses handleDbInputAndResponses;
    @InjectMocks
    private SendEmailTemplateController sendEmailTemplateController;
    private EmailResponse emailResponseExpected = null;

    CompletableFuture<EmailResponse> futureResponseEntityExpected;
    ResponseEntity<EmailResponse> responseEntity;

    CompletableFuture<ResponseEntity<EmailResponse>> futureResponseFromController;

    @Test
    void sendEmailSmallTest() throws Exception {
        EmailRequest emailRequest = EmailRequest.builder()
                .toEmail(new String[]{EMAIL})
                .subject(TEST_SUBJECT)
                .eventName(TEST_EVENT_NAME)
                .eventDate(TEST_EVENT_DATE)
                .eventTime(TEST_EVENT_TIME)
                .eventLocation(TEST_EVENT_LOCATION)
                .eventRegistrationLink(TEST_REGISTRATION_LINK)
                .recipientName(TEST_REGISTRATION_NAME)
                .companyName(TEST_COMPANY_NAME)
                .yourName(TEST_YOUR_NAME)
                .yourJobTitle(TEST_YOUR_JOB_TITLE)
                .signature(SIGNATURE)
                .build();

        EmailTemplateResponse emailTemplateResponseExpected = EmailTemplateResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailRequest)
                .message(LOGGER_MESSAGE_FOR_SIMPLE_MAIL)
                .build();

        ResponseEntity<EmailTemplateResponse> emailTemplateResponseActual = sendEmailTemplateController.sendEmail(emailRequest);

        assertEquals(emailTemplateResponseExpected.toString(), String.valueOf(emailTemplateResponseActual.getBody()));
    }

    @Test
    void testTriggerMail() throws Exception {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(SendEmailTemplateController.EMAIL_LIST)
                .subject(APP_STARTING_SUBJECT)
                .body(APP_STARTING_BODY)
                .file(SIGNATURE)
                .build();

        emailResponseExpected = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(APP_STARTING)
                .build();

        futureResponseEntityExpected = CompletableFuture.completedFuture(emailResponseExpected);

        when(emailSenderService.sendEmailsAppStartsShutdownAsyncWrapper(any(),
                any(),
                any(),
                any(),
                any(Date.class),
                any())).thenReturn(futureResponseEntityExpected);

        futureResponseFromController = sendEmailTemplateController.triggerMail();
        responseEntity = futureResponseFromController.get();

        assertEquals(emailResponseExpected.toString(), String.valueOf(responseEntity.getBody()));
    }

    @Test
    void testTriggerMailOnShutdown() throws Exception {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(SendEmailTemplateController.EMAIL_LIST)
                .subject(APP_SHUTDOWN_SUBJECT)
                .body(APP_SHUTDOWN_BODY)
                .file(SIGNATURE)
                .build();

        emailResponseExpected = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(APP_SHUTDOWN)
                .build();

        futureResponseEntityExpected = CompletableFuture.completedFuture(emailResponseExpected);

        when(emailSenderService.sendEmailsAppStartsShutdownAsyncWrapper(any(),
                any(),
                any(),
                any(),
                any(Date.class),
                any())).thenReturn(futureResponseEntityExpected);

        futureResponseFromController = sendEmailTemplateController.triggerMailOnShutdown();

        responseEntity = futureResponseFromController.get();

        assertEquals(emailResponseExpected.toString(), String.valueOf(responseEntity.getBody()));
    }

}
