package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc

public class SendSimpleMailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @Mock
    private System.Logger logger;

    @InjectMocks
    private SendSimpleMailController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSentEmail() throws Exception {
        String user = "test@example.com";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/mail/sendSimpleEmail")
                        .param("user", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(emailSenderService, times(1)).sendSimpleEmail(new String[]{user}, "User notification", "Get request for user is triggered!");
      //  verify(logger).info("Email to notify that the application has started running GET REQUEST simple email.");
    }
}