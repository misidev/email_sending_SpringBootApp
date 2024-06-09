package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTestSmall {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAllEmailsSuccess() {
        User user1 = new User(1L,
                ApplicationConstants.TEST_USERNAME,
                ApplicationConstants.TEST_EMAIL);

        User user2 = new User(2L,
                ApplicationConstants.TEST_USERNAME,
                ApplicationConstants.TEST_EMAIL_1);

        List<User> userExpected = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(userExpected);

        List<User> emailsActual = userService.findAllUsers();

        assertEquals(userExpected, emailsActual);
    }

    @Test
    void testGetAllEmailsEmptyList() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> emailsActual = userService.findAllUsers();

        assertNotNull(emailsActual);
        assertTrue(emailsActual.isEmpty());
    }

    @Test
    void testGetAllEmailsDBException() {
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> userService.findAllUsers());
    }

}
