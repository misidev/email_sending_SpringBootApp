package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SendEmailControllerSmallTest {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailController sendEmailController;

    EmailTemplateResponse emailTemplateResponseExpected = null;

    @Test
    void testSentEmail() throws Exception {
        MockMultipartFile attachment = new MockMultipartFile("attachments", "test.txt", "text/plain", "Attachment Content".getBytes());

        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(new String[]{ApplicationConstants.TEST_EMAIL},
                ApplicationConstants.TEST_SUBJECT,
                ApplicationConstants.TEST_BODY,
                attachment.getOriginalFilename());

        emailTemplateResponseExpected = new EmailTemplateResponse(ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        EmailTemplateResponse emailTemplateResponseActual = sendEmailController.sentEmail(ApplicationConstants.TEST_EMAIL,
                ApplicationConstants.TEST_SUBJECT,
                ApplicationConstants.TEST_BODY,
                attachment);

        assertEquals(emailTemplateResponseExpected.toString(), emailTemplateResponseActual.toString());
    }

}
