package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.TEST_BODY;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.TEST_SUBJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
class SendMailWithAttachmentControllerTestSmall {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailWithAttachmentController sendMailWithAttachmentController;

    EmailResponse emailResponseExpected = null;

    CompletableFuture<EmailResponse> futureResponseEntityExpected;
    ResponseEntity<EmailResponse> responseEntity;

    CompletableFuture<ResponseEntity<EmailResponse>> futureResponseFromController;

    @Test
    void testSentEmail() throws Exception {
        MockMultipartFile attachment = new MockMultipartFile("attachments",
                "test.txt",
                "text/plain",
                "Attachment Content".getBytes());

        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(new String[]{EMAIL})
                .subject(TEST_SUBJECT)
                .body(TEST_BODY)
                .file(attachment.getOriginalFilename())
                .build();

        emailResponseExpected = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT)
                .build();


        futureResponseEntityExpected = CompletableFuture.completedFuture(emailResponseExpected);

        when(emailSenderService.sendAttachedEmailThroughRequestAsyncWrapper(new String[]{EMAIL},
                TEST_SUBJECT,
                TEST_BODY,
                attachment)).thenReturn(futureResponseEntityExpected);

        futureResponseFromController = sendMailWithAttachmentController.sentEmail(EMAIL,
                TEST_SUBJECT,
                TEST_BODY,
                attachment);

        responseEntity = futureResponseFromController.get();

        assertEquals(emailResponseExpected.toString(), String.valueOf(responseEntity.getBody()));
    }

    @Test
    void testSentEmailWithAttachment() throws Exception {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(new String[]{EMAIL})
                .subject(SUBJECT_FOR_MAIL_WITH_ATTACHMENT)
                .body(BODY_FOR_MAIL_WITH_ATTACHMENT)
                .file(FILE_FOR_MAIL_WITH_ATTACHMENT)
                .build();

        emailResponseExpected = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT)
                .build();


        futureResponseEntityExpected = CompletableFuture.completedFuture(emailResponseExpected);

        when(emailSenderService.sendEmailWithAttachmentAsyncWrapper(EMAIL,
                SUBJECT_FOR_MAIL_WITH_ATTACHMENT,
                BODY_FOR_MAIL_WITH_ATTACHMENT,
                FILE_FOR_MAIL_WITH_ATTACHMENT,
                null,
                null)).thenReturn(futureResponseEntityExpected);

        futureResponseFromController = sendMailWithAttachmentController.sentEmailWithAttachment(EMAIL,
                emailData.getSubject(),
                emailData.getBody(),
                emailData.getFile());

        responseEntity = futureResponseFromController.get();

        assertEquals(emailResponseExpected.toString(), String.valueOf(responseEntity.getBody()));
    }

}
