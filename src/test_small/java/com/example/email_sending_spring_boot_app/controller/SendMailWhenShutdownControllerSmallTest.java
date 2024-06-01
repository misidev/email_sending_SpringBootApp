package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class SendMailWhenShutdownControllerSmallTest {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendMailWhenShutdownController sendMailWhenShutdownController;

    EmailTemplateResponse emailTemplateResponseExpected = null;

    @Test
    void testSentEmail() throws Exception {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(SendMailWhenShutdownController.EMAIL_LIST,
                ApplicationConstants.APP_SHUTDOWN_SUBJECT,
                ApplicationConstants.APP_SHUTDOWN_BODY);

        emailTemplateResponseExpected = new EmailTemplateResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.APP_SHUTDOWN);

        EmailTemplateResponse emailTemplateResponseActual = sendMailWhenShutdownController.triggerMailOnShutdown();

        assertEquals(emailTemplateResponseExpected.toString(), emailTemplateResponseActual.toString());
    }

}
