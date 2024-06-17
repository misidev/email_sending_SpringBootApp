package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.email_sending_spring_boot_app.constants.TestConstants.TEST_REQUEST_BODY_ADD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private UsersController addUsersController;

    @Test
    void testAddingUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mail/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TEST_REQUEST_BODY_ADD)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/mail/users/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
