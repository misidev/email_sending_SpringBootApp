package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.model.Email;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class EmailServiceSmallTest {
    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailService emailService;

    @Test
    void testGetAllEmailsSuccess() {
        List<Email> emailsExpected = Arrays.asList(new Email(), new Email());
        when(emailRepository.findAll()).thenReturn(emailsExpected);

        List<Email> emailsActual = emailService.getAllEmails();

        assertEquals(emailsExpected, emailsActual);
    }

    @Test
    void testGetAllEmailsEmptyList() {
        when(emailRepository.findAll()).thenReturn(Collections.emptyList());

        List<Email> result = emailService.getAllEmails();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllEmailsDBException() {
        when(emailRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> emailService.getAllEmails());
    }
}