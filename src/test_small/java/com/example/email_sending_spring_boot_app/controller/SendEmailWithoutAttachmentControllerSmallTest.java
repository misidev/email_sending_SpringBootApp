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
import static org.mockito.Mockito.when;

@SpringBootTest
class SendEmailWithoutAttachmentControllerSmallTest {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailWithoutAttachmentController sendEmailWithoutAttachmentController;
    EmailResponse emailResponseExpected = null;

    ResponseEntity<EmailResponse> emailResponseActual = null;

    @Test
    void testSentEmail() {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{TEST_EMAIL},
                SUBJECT_FOR_SIMPLE_MAIL,
                BODY_FOR_SIMPLE_MAIL);

        emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_SIMPLE_MAIL);

        when(emailSenderService.sendEmailsWithoutAttachment(new String[]{TEST_EMAIL},
                SUBJECT_FOR_SIMPLE_MAIL,
                BODY_FOR_SIMPLE_MAIL)).thenReturn(emailResponseExpected);

        emailResponseActual = sendEmailWithoutAttachmentController.sentEmail(TEST_EMAIL);

        assertEquals(emailResponseExpected.toString(), String.valueOf(emailResponseActual.getBody()));
    }

}
