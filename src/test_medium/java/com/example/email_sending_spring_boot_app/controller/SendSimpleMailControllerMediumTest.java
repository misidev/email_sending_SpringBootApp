package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class SendSimpleMailControllerMediumTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendSimpleMailController sendSimpleMailController;

    @Test
    void testSentEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/mail/sendSimpleEmail")
                        .param("user", ApplicationConstants.TEST_EMAIL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(ApplicationConstants.TEST_SIMPLE_EMAIL));
    }

}
