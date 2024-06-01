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
class SendMailWithAttachmentControllerSmallTest {

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendMailWithAttachmentController sendMailWithAttachmentController;

    EmailTemplateResponse emailTemplateResponseExpected = null;

    @Test
    void testSentEmail() throws Exception {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(new String[]{ApplicationConstants.TEST_EMAIL},
                ApplicationConstants.SUBJECT_FOR_MAIL_WITH_ATTACHMENT,
                ApplicationConstants.BODY_FOR_MAIL_WITH_ATTACHMENT,
                ApplicationConstants.FILE_NAME);

        emailTemplateResponseExpected = new EmailTemplateResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailTemplate,
                ApplicationConstants.LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        EmailTemplateResponse emailTemplateResponseActual = sendMailWithAttachmentController.sentEmailWithAttachment(ApplicationConstants.TEST_EMAIL);

        assertEquals(emailTemplateResponseExpected.toString(), emailTemplateResponseActual.toString());
    }

}
