package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SendMailWithAttachmentControllerSmallTest {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailWithAttachmentController sendMailWithAttachmentController;

    EmailResponse emailResponseExpected = null;

    ResponseEntity<EmailResponse> emailResponseActual = null;

    @Test
    void testSentEmail() throws Exception {
        MockMultipartFile attachment = new MockMultipartFile("attachments",
                "test.txt",
                "text/plain",
                "Attachment Content".getBytes());

        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{TEST_EMAIL},
                TEST_SUBJECT,
                TEST_BODY,
                attachment.getOriginalFilename());

        emailResponseExpected = new EmailResponse(STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        when(emailSenderService.sendAttachedEmailThroughRequest(new String[]{TEST_EMAIL},
                TEST_SUBJECT,
                TEST_BODY,
                attachment)).thenReturn(emailResponseExpected);

        emailResponseActual = sendMailWithAttachmentController.sentEmail(TEST_EMAIL,
                TEST_SUBJECT,
                TEST_BODY,
                attachment);

        assertEquals(emailResponseExpected.toString(), String.valueOf(emailResponseActual.getBody()));
    }

    @Test
    void testSentEmailWithAttachment() throws Exception {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{TEST_EMAIL},
                SUBJECT_FOR_MAIL_WITH_ATTACHMENT,
                BODY_FOR_MAIL_WITH_ATTACHMENT,
                FILE_FOR_MAIL_WITH_ATTACHMENT);

        emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        when(emailSenderService.sendAttachedEmail(TEST_EMAIL,
                SUBJECT_FOR_MAIL_WITH_ATTACHMENT,
                BODY_FOR_MAIL_WITH_ATTACHMENT,
                FILE_FOR_MAIL_WITH_ATTACHMENT,
                null,
                null)).thenReturn(emailResponseExpected);

        emailResponseActual = sendMailWithAttachmentController.sentEmailWithAttachment(TEST_EMAIL,
                emailData.getSubject(),
                emailData.getBody(),
                emailData.getFile());

        assertEquals(emailResponseExpected.toString(), String.valueOf(emailResponseActual.getBody()));
    }

}
