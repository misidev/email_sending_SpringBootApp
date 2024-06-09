package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import com.example.email_sending_spring_boot_app.util.HandleDbInputAndResponses;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class EmailSenderServiceTestSmall {

    @Mock
    private EmailRepository emailRepository;

    private EmailSenderService emailSenderService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMailMessage mimeMailMessage;

    @Mock
    private SimpleMailMessage simpleMailMessage;

    @InjectMocks
    private HandleDbInputAndResponses handleDbInputAndResponses;


    @Mock
    private HttpServletResponse httpServletResponse;

    @Value("${spring.mail.username}")
    String mailSenderUsername;

    @Test
     void testSendEmailsWithoutAttachment() {
        // Arrange
        emailSenderService = new EmailSenderService();

        EmailResponse.EmailData emailData = new EmailResponse.EmailData(EMAIL_LIST,
                SUBJECT_FOR_SIMPLE_MAIL,
                BODY_FOR_SIMPLE_MAIL);

        EmailResponse emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_SIMPLE_MAIL);

        //when(handleDbInputAndResponses.handleSuccessResponseSimple(TEST_EMAIL)).thenReturn(emailResponseExpected);

        // Act
        EmailResponse emailResponseActual = emailSenderService.sendEmailsWithoutAttachment(EMAIL_LIST,
                SUBJECT_FOR_SIMPLE_MAIL,
                BODY_FOR_SIMPLE_MAIL);

        // Assert
        // Verify that javaMailSender.send() is called with the correct parameters
        verify(javaMailSender).send((MimeMessage) argThat(argument -> {
            SimpleMailMessage message = (SimpleMailMessage) argument;
            return message.getFrom().equals(mailSenderUsername) &&
                    Arrays.equals(message.getTo(), EMAIL_LIST) &&
                    message.getSubject().equals(SUBJECT_FOR_SIMPLE_MAIL) &&
                    message.getText().equals(BODY_FOR_SIMPLE_MAIL);
        }));

        // Add assertions for the response object if needed
    }

//    @Test
//    void sendEmailsWithoutAttachmentTest() {
////        String emailResponseExpected = "test";
//
//        EmailResponse.EmailData emailData = new EmailResponse.EmailData(EMAIL_LIST,
//                SUBJECT_FOR_SIMPLE_MAIL,
//                BODY_FOR_SIMPLE_MAIL);
//
//        EmailResponse emailResponseExpected = new EmailResponse(
//                STATUS_SUCCESS,
//                HttpStatus.OK,
//                emailData,
//                LOGGER_MESSAGE_FOR_SIMPLE_MAIL);
//
//        when(handleDbInputAndResponses.handleSuccessResponseSimple(TEST_EMAIL)).thenReturn(emailResponseExpected);
//
//        EmailResponse emailResponseActual = emailSenderService.sendEmailsWithoutAttachment(EMAIL_LIST,
//                SUBJECT_FOR_SIMPLE_MAIL,
//                BODY_FOR_SIMPLE_MAIL);
//       // assertEquals(emailResponseExpected.toString(), emailResponseActual.toString());
//    }

//    @Test
//    void sendAttachedEmailTest() {
//
//
//
//
//        EmailResponse emailResponseActual = emailSenderService.sendAttachedEmail(EMAIL_LIST,
//                SUBJECT_FOR_SIMPLE_MAIL,
//                BODY_FOR_SIMPLE_MAIL);
//        assertEquals(emailResponseExpected.toString(), emailResponseActual.toString());
//    }


    @Test
    void sendAttachedEmailThroughRequestTest() {
        MockMultipartFile attachment = new MockMultipartFile("attachments", "test.txt", "text/plain", "Attachment Content".getBytes());
//        MimeMessage mimeMessageMock = mock(MimeMessage.class);
//        MimeMessageHelper mimeMessageHelper = mock(MimeMessageHelper.class);
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{TEST_EMAIL},
                TEST_SUBJECT,
                TEST_BODY,
                attachment.getOriginalFilename());

        EmailResponse emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        when(handleDbInputAndResponses.handleSuccessResponseMultipartFile(TEST_EMAIL, TEST_SUBJECT, TEST_BODY, attachment)).thenReturn(emailResponseExpected);

        EmailResponse emailResponse = emailSenderService.sendAttachedEmailThroughRequest(new String[]{TEST_EMAIL}, TEST_SUBJECT, TEST_BODY, attachment);

        assertEquals(emailResponseExpected,emailResponse);
    }

//    @Test
//    void testGetAllEmailsSuccess() {
//        List<Email> emailsExpected = Arrays.asList(new Email(), new Email());
//        when(emailRepository.findAll()).thenReturn(emailsExpected);
//
//        List<Email> emailsActual = emailSenderService.getAllEmails();
//
//        assertEquals(emailsExpected, emailsActual);
//    }
//
//    @Test
//    void testGetAllEmailsEmptyList() {
//        when(emailRepository.findAll()).thenReturn(Collections.emptyList());
//
//        List<Email> result = emailSenderService.getAllEmails();
//
//        assertNotNull(result);
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void testGetAllEmailsDBException() {
//        when(emailRepository.findAll()).thenThrow(new RuntimeException("Database error"));
//
//        assertThrows(RuntimeException.class, () -> emailSenderService.getAllEmails());
//    }

}
