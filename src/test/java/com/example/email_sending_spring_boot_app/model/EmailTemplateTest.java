package com.example.email_sending_spring_boot_app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTemplateTest {
    String[] emailAddress = new String[]{"milicasimovic888@yahoo.com"};
    String body = "This is test body for email!";
    String subject = "This is test subject of email!";
    String file = "This is test file!";
    EmailTemplate emailTemplate=new EmailTemplate();

    @Test
    public void testSettersAndGettersEmail() {
        emailTemplate.setToEmail(emailAddress);
        emailTemplate.setBody(body);
        emailTemplate.setSubject(subject);
        emailTemplate.setFile(file);

        assertEquals(emailAddress, emailTemplate.getToEmail());
        assertEquals(body, emailTemplate.getBody());
        assertEquals(subject, emailTemplate.getSubject());
        assertEquals(file, emailTemplate.getFile());
    }
    @Test
    public void testSimpleEmail() {
        emailTemplate = new EmailTemplate(emailAddress, subject, body);

        assertEquals(emailAddress, emailTemplate.getToEmail());
        assertEquals(body, emailTemplate.getBody());
        assertEquals(subject, emailTemplate.getSubject());
    }
    @Test
    public void testEmailWithAttachment() {
        emailTemplate = new EmailTemplate(emailAddress, subject, body,file);

        assertEquals(emailAddress, emailTemplate.getToEmail());
        assertEquals(body, emailTemplate.getBody());
        assertEquals(subject, emailTemplate.getSubject());
        assertEquals(file, emailTemplate.getFile());
    }

}
