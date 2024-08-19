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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
class SendEmailWithoutAttachmentControllerTestSmall {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailWithoutAttachmentController sendEmailWithoutAttachmentController;
    EmailResponse emailResponseExpected = null;

    CompletableFuture<EmailResponse> futureResponseEntityExpected;
    ResponseEntity<EmailResponse> responseEntity;

    CompletableFuture<ResponseEntity<EmailResponse>> futureResponseFromController;

    @Test
    void testSentEmail() throws ExecutionException, InterruptedException {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(new String[]{EMAIL})
                .subject(SUBJECT_FOR_SIMPLE_MAIL)
                .body(BODY_FOR_SIMPLE_MAIL)
                .file(null)
                .build();

        emailResponseExpected = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(LOGGER_MESSAGE_FOR_SIMPLE_MAIL)
                .build();

        futureResponseEntityExpected = CompletableFuture.completedFuture(emailResponseExpected);


        when(emailSenderService.sendEmailWithoutAttachmentAsyncWrapper(new String[]{EMAIL},
                SUBJECT_FOR_SIMPLE_MAIL,
                BODY_FOR_SIMPLE_MAIL)).thenReturn(futureResponseEntityExpected);

        futureResponseFromController = sendEmailWithoutAttachmentController.sentEmail(EMAIL);

        responseEntity = futureResponseFromController.get();

        assertEquals(emailResponseExpected.toString(), String.valueOf(responseEntity.getBody()));
    }

}
