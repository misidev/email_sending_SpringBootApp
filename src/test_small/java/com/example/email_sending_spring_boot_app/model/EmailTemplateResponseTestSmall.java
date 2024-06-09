package com.example.email_sending_spring_boot_app.model;

import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailTemplateResponseTestSmall {

    @Test
    void testEmailResponse() {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{TEST_EMAIL},
                TEST_SUBJECT,
                TEST_BODY,
                TEST_FILE);

        EmailResponse emailTemplateResponse = new EmailResponse(STATUS_SUCCESS,
                HttpStatus.valueOf(CODE_OK),
                emailData,
                TEST_ERROR_MESSAGE);

        assertArrayEquals(new String[]{TEST_EMAIL}, emailTemplateResponse.getData().getToEmail());
        assertEquals(TEST_SUBJECT, emailTemplateResponse.getData().getSubject());
        assertEquals(TEST_BODY, emailTemplateResponse.getData().getBody());
        assertEquals(TEST_FILE, emailTemplateResponse.getData().getFile());
        assertEquals(STATUS_SUCCESS, emailTemplateResponse.getStatus());
        assertEquals(HttpStatus.valueOf(CODE_OK), emailTemplateResponse.getCode());
        assertEquals(TEST_ERROR_MESSAGE, emailTemplateResponse.getMessage());
    }

    @Test
    void setToEmail() {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData();
        emailData.setToEmail(new String[]{TEST_EMAIL});
        emailData.setSubject(TEST_SUBJECT);
        emailData.setBody(TEST_BODY);
        emailData.setFile(TEST_FILE);

        EmailResponse emailTemplateResponse = new EmailResponse(null,
                null,
                emailData,
                null);
        emailTemplateResponse.setStatus(STATUS_SUCCESS);
        emailTemplateResponse.setCode(HttpStatus.valueOf(CODE_OK));
        emailTemplateResponse.setMessage(TEST_ERROR_MESSAGE);

        assertArrayEquals(new String[]{TEST_EMAIL}, emailTemplateResponse.getData().getToEmail());
        assertEquals(TEST_SUBJECT, emailTemplateResponse.getData().getSubject());
        assertEquals(TEST_BODY, emailTemplateResponse.getData().getBody());
        assertEquals(TEST_FILE, emailTemplateResponse.getData().getFile());
        assertEquals(STATUS_SUCCESS, emailTemplateResponse.getStatus());
        assertEquals(HttpStatus.valueOf(CODE_OK), emailTemplateResponse.getCode());
        assertEquals(TEST_ERROR_MESSAGE, emailTemplateResponse.getMessage());
    }

}
