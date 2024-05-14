package com.example.email_sending_spring_boot_app.medium_tests.controller;

import com.example.email_sending_spring_boot_app.controller.SendMailWithAttachmentController;
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

@SpringBootTest
@AutoConfigureMockMvc
class SendMailWithAttachmentControllerMediumTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendMailWithAttachmentController sendMailWithAttachmentController;

    @Test
    void testSentEmail() throws Exception {
        String toEmail = "milicasimovic77@yahoo.com";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/mail/sendEmailWithAttachment")
                        .param("user", toEmail)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
