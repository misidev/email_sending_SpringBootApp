package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SendMailWhenShutdownControllerTestSmall {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendMailWhenShutdownController sendMailWhenShutdownController;

    EmailResponse emailTemplateResponseExpected = null;

    @Test
    void testSentEmail() throws Exception {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(SendMailWhenShutdownController.EMAIL_LIST,
                APP_SHUTDOWN_SUBJECT,
                APP_SHUTDOWN_BODY,
                SIGNATURE);

        emailTemplateResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                APP_SHUTDOWN);

        ResponseEntity<EmailResponse> emailTemplateResponseActual = sendMailWhenShutdownController.triggerMailOnShutdown();

        assertEquals(emailTemplateResponseExpected.toString(), emailTemplateResponseActual.toString());
    }

}
