package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.EMAIL;
import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.EMAIL_1;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class GetAllEmailsTestSmall {
    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private GetAllEmailsController getAllEmailsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        Email email1 = new Email(TEST_ID,
                EMAIL_1,
                EMAIL,
                TEST_SUBJECT,
                TEST_BODY,
                TEST_TIMESTAMP);

        Email email2 = new Email(TEST_ID_2,
                EMAIL,
                EMAIL_1,
                TEST_SUBJECT,
                TEST_BODY,
                TEST_TIMESTAMP);

        when(emailRepository.findAll()).thenReturn(Arrays.asList(email1, email2));

        ResponseEntity<List<Email>> response = getAllEmailsController.getAllEmails();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(email1, email2), response.getBody());
    }

    @Test
    void testGetAllUsers500() {
        when(emailRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<List<Email>> response = getAllEmailsController.getAllEmails();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}
