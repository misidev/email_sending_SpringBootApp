package com.example.email_sending_spring_boot_app.small_tests.controller;

import com.example.email_sending_spring_boot_app.controller.SendMailWithAttachmentController;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class SendMailWithAttachmentControllerSmallTest {

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendMailWithAttachmentController sendMailWithAttachmentController;

    @Test
    void testSentEmail() throws Exception {
        String toEmail = "milicasimovic77@yahoo.com";

        sendMailWithAttachmentController.sentEmailWithAttachment(toEmail);
    }

}
