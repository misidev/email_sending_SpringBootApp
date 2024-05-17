package com.example.email_sending_spring_boot_app.small_tests.model;

import com.example.email_sending_spring_boot_app.model.EmailTemplateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailTemplateSmallTest {

    @Test
    void testSimpleEmail() {
        String[] emailAddress = {"milicasimovic77@yahoo.com"};
        String body = "This is test body for email!";
        String subject = "This is test subject of email!";

        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate();
        emailTemplate.setToEmail(new String[]{"milicasimovic77@yahoo.com"});
        emailTemplate.setBody("This is test body for email!");
        emailTemplate.setSubject("This is test subject of email!");

        assertEquals(body, emailTemplate.getBody());
        assertEquals(subject, emailTemplate.getSubject());
    }

    @Test
    void setToEmail() {
        String[] emailAddress = {"milicasimovic77@yahoo.com"};
        String body = "This is test body for email!";
        String subject = "This is test subject of email!";
        String file = "This is test file!";

        EmailTemplateResponse.EmailTemplate emailTemplate = new EmailTemplateResponse.EmailTemplate();
        emailTemplate.setToEmail(new String[]{"milicasimovic77@yahoo.com"});
        emailTemplate.setBody("This is test body for email!");
        emailTemplate.setSubject("This is test subject of email!");
        emailTemplate.setFile("This is test file!");

        assertEquals(body, emailTemplate.getBody());
        assertEquals(subject, emailTemplate.getSubject());
        assertEquals(file, emailTemplate.getFile());
    }

}
