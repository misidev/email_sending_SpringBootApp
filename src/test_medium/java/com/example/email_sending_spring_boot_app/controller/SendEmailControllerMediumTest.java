package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SendEmailControllerMediumTest {
//NOT DONE
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailController sendEmailController;

    @Test
    void testFileUpload() throws Exception {
        String subject = "Test Subject";
        String body = "Test Body";
        MockMultipartFile attachment = new MockMultipartFile("attachments", "test.txt", "text/plain", "Attachment Content".getBytes());

        // Perform the request
        mockMvc.perform(multipart("/api/mail/sendEmail")
                        .file(attachment)
                        .param("user", ApplicationConstants.TEST_EMAIL)
                        .param("subject", subject)
                        .param("body", body))
                .andExpect(status().isOk())
                .andExpect(content().string(ApplicationConstants.TEST_EMAIL_RESPONSE));
    }

}
