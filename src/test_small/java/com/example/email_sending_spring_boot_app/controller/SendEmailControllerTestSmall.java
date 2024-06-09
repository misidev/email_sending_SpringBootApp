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

@SpringBootTest
class SendEmailControllerTestSmall {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailController sendEmailController;

    EmailResponse emailTemplateResponseExpected = null;

    @Test
    void testSentEmail() throws Exception {
        MockMultipartFile attachment = new MockMultipartFile("attachments", "test.txt", "text/plain", "Attachment Content".getBytes());

        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{TEST_EMAIL},
                TEST_SUBJECT,
                TEST_BODY,
                attachment.getOriginalFilename());

        emailTemplateResponseExpected = new EmailResponse(STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        ResponseEntity<EmailResponse> emailTemplateResponseActual = sendEmailController.sentEmail(TEST_EMAIL,
                TEST_SUBJECT,
                TEST_BODY,
                attachment);

        assertEquals(emailTemplateResponseExpected.toString(), emailTemplateResponseActual.toString());
    }

}
