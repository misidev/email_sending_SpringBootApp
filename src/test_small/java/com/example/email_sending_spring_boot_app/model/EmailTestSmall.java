package com.example.email_sending_spring_boot_app.model;

import com.example.email_sending_spring_boot_app.model.entity.Email;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailTestSmall {

    @Test
    void testEmailConstructorAndGetters() {
        Email email = new Email(TEST_ID,
                TEST_EMAIL,
                TEST_EMAIL_1,
                TEST_SUBJECT,
                TEST_BODY,
                TEST_TIMESTAMP);

        assertEquals(TEST_ID, email.getId());
        assertEquals(TEST_EMAIL, email.getSender());
        assertEquals(TEST_EMAIL_1, email.getRecipient());
        assertEquals(TEST_SUBJECT, email.getSubject());
        assertEquals(TEST_BODY, email.getBody());
        assertEquals(TEST_TIMESTAMP, email.getTimestamp());
    }

    @Test
    void testSetters() {
        Email email = new Email();

        email.setId(TEST_ID);
        email.setSender(TEST_EMAIL);
        email.setRecipient(TEST_EMAIL_1);
        email.setSubject(TEST_SUBJECT);
        email.setBody(TEST_BODY);
        email.setTimestamp(TEST_TIMESTAMP);

        assertEquals(TEST_ID, email.getId());
        assertEquals(TEST_EMAIL, email.getSender());
        assertEquals(TEST_EMAIL_1, email.getRecipient());
        assertEquals(TEST_SUBJECT, email.getSubject());
        assertEquals(TEST_BODY, email.getBody());
        assertEquals(TEST_TIMESTAMP, email.getTimestamp());
    }

}
