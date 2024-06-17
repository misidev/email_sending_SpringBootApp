package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SendMailWithAttachmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailWithAttachmentController sendEmailWithAttachmentController;

    @Test
    void testSentEmailMultipartFile() throws Exception {
        MockMultipartFile attachment = new MockMultipartFile("attachments", "test.txt", "text/plain", "Attachment Content".getBytes());

        mockMvc.perform(multipart("/api/v1/mail/sendEmail")
                        .file(attachment)
                        .param(USER, EMAIL)
                        .param(SUBJECT, TEST_SUBJECT)
                        .param(BODY, TEST_BODY))
                .andExpect(status().isOk());
    }

    @Test
    void testSentEmailWithAttachment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/mail/sendEmailWithAttachment")
                        .param(USER, EMAIL)
                        .param(SUBJECT, SUBJECT_FOR_MAIL_WITH_ATTACHMENT)
                        .param(BODY, BODY_FOR_MAIL_WITH_ATTACHMENT)
                        .param(FILE, FILE_FOR_MAIL_WITH_ATTACHMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(TEST_EMAIL_WITH_ATTACHMENT));
    }

}
