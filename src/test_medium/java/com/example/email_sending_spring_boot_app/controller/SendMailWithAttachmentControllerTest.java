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

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class SendMailWithAttachmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendMailWithAttachmentController sendMailWithAttachmentController;

    @Test
    void testSentEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/mail/sendEmailWithAttachment")
                        .param(USER, TEST_EMAIL)
                        .param(SUBJECT, SUBJECT_FOR_MAIL_WITH_ATTACHMENT)
                        .param(BODY, BODY_FOR_MAIL_WITH_ATTACHMENT)
                        .param(FILE, FILE_FOR_MAIL_WITH_ATTACHMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(TEST_EMAIL_WITH_ATTACHMENT));
    }

}
