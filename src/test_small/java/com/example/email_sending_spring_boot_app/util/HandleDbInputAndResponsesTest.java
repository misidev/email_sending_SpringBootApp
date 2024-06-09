package com.example.email_sending_spring_boot_app.util;

import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class HandleDbInputAndResponsesTest {
    @InjectMocks
    private HandleDbInputAndResponses handleDbInputAndResponses;

    @Mock
    private EmailRepository emailRepository;
    @Mock
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    public void setUp() {
        handleDbInputAndResponses = new HandleDbInputAndResponses();
    }

    @Test
    void addingEmailTest() {
        Email email = new Email(TEST_ID,
                TEST_EMAIL_1,
                TEST_EMAIL,
                TEST_SUBJECT,
                TEST_BODY,
                TEST_TIMESTAMP);

        Email emailActual = handleDbInputAndResponses.addingEmail(TEST_SUBJECT,
                TEST_BODY,
                TEST_EMAIL,
                TEST_EMAIL_1,
                TEST_TIMESTAMP);
        emailActual.setId(TEST_ID);
        assertEquals(email.toString(), emailActual.toString());
    }

    @Test
    void saveEmailsTest() {
        Email email1 = new Email(TEST_ID,
                TEST_EMAIL_1,
                TEST_EMAIL,
                TEST_SUBJECT,
                TEST_BODY,
                TEST_TIMESTAMP);

//        when(handleDbInputAndResponses.addingEmail(TEST_SUBJECT,
//                TEST_BODY,
//                TEST_EMAIL,
//                TEST_EMAIL_1,
//                TEST_TIMESTAMP)).thenReturn(email1);
//        doReturn(email1).when(handleDbInputAndResponses).addingEmail(TEST_SUBJECT,
//                TEST_BODY,
//                TEST_EMAIL,
//                TEST_EMAIL_1,
//                TEST_TIMESTAMP);

        handleDbInputAndResponses.saveEmail(TEST_EMAIL, TEST_SUBJECT, TEST_BODY, TEST_EMAIL_1);

//        // Verify that emailRepository.save() is called with the correct parameters
//        verify(emailRepository).save(argThat(email -> email.getRecipient().equals(TEST_EMAIL) &&
//                email.getSubject().equals(TEST_SUBJECT) &&
//                email.getBody().equals(TEST_BODY) &&
//                email.getSender().equals(TEST_EMAIL_1) &&
//                email.getTimestamp() != null));
    }

    @Test
    void saveEmail() {
    }

    @Test
    void handleUnauthorized() {
    }

    @Test
    void handleInternalServerError() {
    }

    @Test
    void handleServiceUnavailable() {
    }

    @Test
    void handleSuccessResponseAppStarts() {
    }

    @Test
    void handleSuccessResponseShutdown() {
    }

    @Test
    void handleSuccessResponseAttachment() {
    }

    @Test
    void handleSuccessResponseSimple() {
    }

    @Test
    void handleSuccessResponseMultipartFile() {
    }
}