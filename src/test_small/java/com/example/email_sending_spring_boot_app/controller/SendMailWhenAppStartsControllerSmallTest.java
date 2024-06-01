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
class SendMailWhenAppStartsControllerSmallTest {
    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendMailWhenAppStartsController sendMailWhenAppStartsController;

    EmailTemplateResponse emailTemplateResponseExpected = null;

    @Test
    void testSentEmail() throws Exception {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(SendMailWhenAppStartsController.EMAIL_LIST,
                ApplicationConstants.APP_STARTING_SUBJECT,
                ApplicationConstants.APP_STARTING_BODY);

        emailTemplateResponseExpected = new EmailTemplateResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.APP_STARTING);


        EmailTemplateResponse emailTemplateResponseActual = sendMailWhenAppStartsController.triggerMail();

        assertEquals(emailTemplateResponseExpected.toString(), emailTemplateResponseActual.toString());
    }
}