package com.example.email_sending_spring_boot_app.model;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailSmallTest {

    @Test
    void testEmailConstructorAndGetters() {
        Email email = new Email(ApplicationConstants.TEST_ID,
                ApplicationConstants.TEST_EMAIL,
                ApplicationConstants.TEST_EMAIL_1,
                ApplicationConstants.TEST_SUBJECT,
                ApplicationConstants.TEST_BODY, ApplicationConstants.TEST_TIMESTAMP);

        assertEquals(ApplicationConstants.TEST_ID, email.getId());
        assertEquals(ApplicationConstants.TEST_EMAIL, email.getSender());
        assertEquals(ApplicationConstants.TEST_EMAIL_1, email.getRecipient());
        assertEquals(ApplicationConstants.TEST_SUBJECT, email.getSubject());
        assertEquals(ApplicationConstants.TEST_BODY, email.getBody());
        assertEquals(ApplicationConstants.TEST_TIMESTAMP, email.getTimestamp());
    }

    @Test
    void testSetters() {
        Email email = new Email();

        email.setId(ApplicationConstants.TEST_ID);
        email.setSender(ApplicationConstants.TEST_EMAIL);
        email.setRecipient(ApplicationConstants.TEST_EMAIL_1);
        email.setSubject(ApplicationConstants.TEST_SUBJECT);
        email.setBody(ApplicationConstants.TEST_BODY);
        email.setTimestamp(ApplicationConstants.TEST_TIMESTAMP);

        assertEquals(ApplicationConstants.TEST_ID, email.getId());
        assertEquals(ApplicationConstants.TEST_EMAIL, email.getSender());
        assertEquals(ApplicationConstants.TEST_EMAIL_1, email.getRecipient());
        assertEquals(ApplicationConstants.TEST_SUBJECT, email.getSubject());
        assertEquals(ApplicationConstants.TEST_BODY, email.getBody());
        assertEquals(ApplicationConstants.TEST_TIMESTAMP, email.getTimestamp());
    }
}
