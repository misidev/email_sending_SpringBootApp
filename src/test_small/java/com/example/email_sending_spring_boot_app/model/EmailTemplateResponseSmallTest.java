package com.example.email_sending_spring_boot_app.model;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailTemplateResponseSmallTest {

    @Test
    void testEmailResponse() {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate(new String[]{ApplicationConstants.TEST_EMAIL},
                ApplicationConstants.TEST_SUBJECT,
                ApplicationConstants.TEST_BODY,
                ApplicationConstants.TEST_FILE);

        EmailTemplateResponse emailTemplateResponse = new EmailTemplateResponse(ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.valueOf(ApplicationConstants.CODE_OK),
                emailTemplate,
                ApplicationConstants.TEST_ERROR_MESSAGE);

        assertArrayEquals(new String[]{ApplicationConstants.TEST_EMAIL}, emailTemplateResponse.getData().getToEmail());
        assertEquals(ApplicationConstants.TEST_SUBJECT, emailTemplateResponse.getData().getSubject());
        assertEquals(ApplicationConstants.TEST_BODY, emailTemplateResponse.getData().getBody());
        assertEquals(ApplicationConstants.TEST_FILE, emailTemplateResponse.getData().getFile());
        assertEquals(ApplicationConstants.STATUS_SUCCESS, emailTemplateResponse.getStatus());
        assertEquals(HttpStatus.valueOf(ApplicationConstants.CODE_OK), emailTemplateResponse.getCode());
        assertEquals(ApplicationConstants.TEST_ERROR_MESSAGE, emailTemplateResponse.getMessage());
    }

    @Test
    void setToEmail() {
        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate();
        emailTemplate.setToEmail(new String[]{ApplicationConstants.TEST_EMAIL});
        emailTemplate.setSubject(ApplicationConstants.TEST_SUBJECT);
        emailTemplate.setBody(ApplicationConstants.TEST_BODY);
        emailTemplate.setFile(ApplicationConstants.TEST_FILE);

        EmailTemplateResponse emailTemplateResponse = new EmailTemplateResponse(null,
                null,
                emailTemplate,
                null);
        emailTemplateResponse.setStatus(ApplicationConstants.STATUS_SUCCESS);
        emailTemplateResponse.setCode(HttpStatus.valueOf(ApplicationConstants.CODE_OK));
        emailTemplateResponse.setMessage(ApplicationConstants.TEST_ERROR_MESSAGE);

        assertArrayEquals(new String[]{ApplicationConstants.TEST_EMAIL}, emailTemplateResponse.getData().getToEmail());
        assertEquals(ApplicationConstants.TEST_SUBJECT, emailTemplateResponse.getData().getSubject());
        assertEquals(ApplicationConstants.TEST_BODY, emailTemplateResponse.getData().getBody());
        assertEquals(ApplicationConstants.TEST_FILE, emailTemplateResponse.getData().getFile());
        assertEquals(ApplicationConstants.STATUS_SUCCESS, emailTemplateResponse.getStatus());
        assertEquals(HttpStatus.valueOf(ApplicationConstants.CODE_OK), emailTemplateResponse.getCode());
        assertEquals(ApplicationConstants.TEST_ERROR_MESSAGE, emailTemplateResponse.getMessage());
    }

}
