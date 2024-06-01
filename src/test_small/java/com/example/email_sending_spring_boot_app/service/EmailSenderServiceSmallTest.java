package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
class EmailSenderServiceSmallTest {

    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private EmailSenderService emailSenderService;

    String[] toEmail = {ApplicationConstants.TEST_EMAIL};
    String subject = "Test Subject";
    String body = "Test Body";
    String file = "path/to/attachment.jpg";

    @Test
    void sendSimpleEmail_shouldSendEmail() {
        emailSenderService.sendSimpleEmail(toEmail, subject, body);
    }

    @Test
    void sendAttachedEmail_shouldSendEmailWithAttachment() throws MessagingException, IOException {
        emailSenderService.sendAttachedEmail(toEmail, subject, body, null);
    }

}
