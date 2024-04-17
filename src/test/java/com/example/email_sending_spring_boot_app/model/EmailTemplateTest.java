package com.example.email_sending_spring_boot_app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTemplateTest {

    @Test
    void testSimpleEmail() {
        String[] emailAddress = {"milicasimovic77@yahoo.com"};
        String body = "This is test body for email!";
        String subject = "This is test subject of email!";

        EmailTemplate emailTemplate = new EmailTemplate();
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
        String file="This is test file!";

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setToEmail(new String[]{"milicasimovic77@yahoo.com"});
        emailTemplate.setBody("This is test body for email!");
        emailTemplate.setSubject("This is test subject of email!");
        emailTemplate.setFile("This is test file!");

        assertEquals(body, emailTemplate.getBody());
        assertEquals(subject, emailTemplate.getSubject());
        assertEquals(file, emailTemplate.getFile());
    }

}
