package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class GetAllUsersControllerSmallTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetAllUsersController getAllUsersController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1L,
                ApplicationConstants.TEST_USERNAME,
                ApplicationConstants.TEST_EMAIL);

        User user2 = new User(2L,
                ApplicationConstants.TEST_USERNAME,
                ApplicationConstants.TEST_EMAIL_1);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<List<User>> response = getAllUsersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(user1, user2), response.getBody());
    }

    @Test
    void testGetAllUsers500() {
        // Mock the userRepository behavior to throw an exception
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Call the controller method
        ResponseEntity<List<User>> response = getAllUsersController.getAllUsers();

        // Verify the response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}
