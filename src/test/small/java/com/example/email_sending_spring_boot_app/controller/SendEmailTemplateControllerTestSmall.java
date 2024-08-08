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
        EmailRequest emailRequest = new EmailRequest(new String[]{EMAIL},
                TEST_SUBJECT,
                TEST_EVENT_NAME,
                TEST_EVENT_DATE,
                TEST_EVENT_TIME,
                TEST_EVENT_LOCATION,
                TEST_REGISTRATION_LINK,
                TEST_REGISTRATION_NAME,
                TEST_COMPANY_NAME,
                TEST_YOUR_NAME,
                TEST_YOUR_JOB_TITLE,
                SIGNATURE);

        EmailTemplateResponse emailTemplateResponseExpected = new EmailTemplateResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailRequest,
                LOGGER_MESSAGE_FOR_SIMPLE_MAIL);

        ResponseEntity<EmailTemplateResponse> emailTemplateResponseActual = sendEmailTemplateController.sendEmail(emailRequest);

        assertEquals(emailTemplateResponseExpected.toString(), String.valueOf(emailTemplateResponseActual.getBody()));
    }

    @Test
    void testTriggerMail() throws Exception {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(SendEmailTemplateController.EMAIL_LIST,
                APP_STARTING_SUBJECT,
                APP_STARTING_BODY,
                SIGNATURE);

        emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                APP_STARTING);

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
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(SendEmailTemplateController.EMAIL_LIST,
                APP_SHUTDOWN_SUBJECT,
                APP_SHUTDOWN_BODY,
                SIGNATURE);

        emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                APP_SHUTDOWN);

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
