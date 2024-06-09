package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetAllUsersControllerTestSmall {
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
                TEST_USERNAME,
                TEST_EMAIL);

        User user2 = new User(2L,
                TEST_USERNAME,
                TEST_EMAIL_1);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<List<User>> response = getAllUsersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(user1, user2), response.getBody());
    }

    @Test
    void testGetAllUsers500() {
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<List<User>> response = getAllUsersController.getAllUsers();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}
