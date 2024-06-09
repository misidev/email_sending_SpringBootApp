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
class SendMailWithAttachmentControllerTestSmall {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendMailWithAttachmentController sendMailWithAttachmentController;

    EmailResponse emailTemplateResponseExpected = null;

    @Test
    void testSentEmail() throws Exception {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{TEST_EMAIL},
                SUBJECT_FOR_MAIL_WITH_ATTACHMENT,
                BODY_FOR_MAIL_WITH_ATTACHMENT,
                FILE_FOR_MAIL_WITH_ATTACHMENT);

        emailTemplateResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        ResponseEntity<EmailResponse> emailTemplateResponseActual = sendMailWithAttachmentController.sentEmailWithAttachment(TEST_EMAIL,
                emailData.getSubject(),
                emailData.getBody(),
                emailData.getFile());

        assertEquals(emailTemplateResponseExpected.toString(), emailTemplateResponseActual.toString());
    }

}
