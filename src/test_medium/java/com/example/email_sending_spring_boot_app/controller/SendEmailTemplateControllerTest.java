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

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.TEST_REQUEST_BODY_TEMPLATE;

@SpringBootTest
@AutoConfigureMockMvc
class SendEmailTemplateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailTemplateController sendEmailTemplateController;

    @Test
    void testSentEmailWithTemplate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/mail/send-email-template")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TEST_REQUEST_BODY_TEMPLATE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
