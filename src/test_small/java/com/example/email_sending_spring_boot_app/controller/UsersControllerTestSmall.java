package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.model.response.UserResponse;
import com.example.email_sending_spring_boot_app.model.response.UsersResponse;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import com.example.email_sending_spring_boot_app.util.HandleDbInputAndResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.TEST_USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
class UsersControllerTestSmall {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UsersController usersController;

    @Mock
    private HandleDbInputAndResponses handleDbInputAndResponses;
    private User user1;

    private User user2;
    private List<User> users;

    @BeforeEach
    public void setUp() {
        user1 = new User(1L, TEST_USERNAME, EMAIL);
        user2 = new User(2L, TEST_USERNAME, EMAIL_1);
        users = Arrays.asList(user1, user2);
    }

    @Test
    void testGetAllUsers() {
        UsersResponse usersResponse = new UsersResponse(STATUS_SUCCESS,
                HttpStatus.OK,
                users,
                LOGGER_MESSAGE_GET_ALL_USERS);

        when(userRepository.findAll()).thenReturn(users);
        when(handleDbInputAndResponses.handleSuccessResponseGetUsers(users)).thenReturn(usersResponse);

        ResponseEntity<UsersResponse> response = usersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usersResponse.toString(), String.valueOf(response.getBody()));
    }

    @Test
    void testAddAllUsers() {
        UserResponse userResponse = new UserResponse(STATUS_SUCCESS,
                HttpStatus.OK,
                user1,
                LOGGER_MESSAGE_ADD_USER);


        when(userRepository.save(any())).thenReturn(user1);
        when(handleDbInputAndResponses.handleSuccessResponseAddUser(user1)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = usersController.addUser(user1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse.toString(), String.valueOf(response.getBody()));
    }

}
