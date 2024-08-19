package com.example.email_sending_spring_boot_app.controller;

import com.example.email_sending_spring_boot_app.repository.UserRepository;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import com.example.email_sending_spring_boot_app.util.HandleDbInputAndResponses;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.EMAIL;
import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.USER;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.TEST_EMAIL_WITHOUT_ATTACHMENT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@SpringBootTest
@AutoConfigureMockMvc
class SendEmailWithoutAttachmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private SendEmailWithoutAttachmentController sendEmailWithoutAttachmentController;

    @Mock
    private HandleDbInputAndResponses handleDbInputAndResponses;

    @Mock
    private UserRepository userRepository;

    @Test
    void testSentEmailWithoutAttachment() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/mail/sendEmailWithoutAttachment")
                        .param(USER, EMAIL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(TEST_EMAIL_WITHOUT_ATTACHMENT));
    }

}
