package com.example.email_sending_spring_boot_app.model;

import com.example.email_sending_spring_boot_app.model.entity.Email;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.EMAIL;
import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.EMAIL_1;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
class EmailTestSmall {

    @Test
    void testEmailConstructorAndGetters() {
        Email email = new Email(TEST_ID,
                EMAIL,
                EMAIL_1,
                TEST_SUBJECT,
                TEST_BODY,
                TEST_TIMESTAMP);

        assertEquals(TEST_ID, email.getId());
        assertEquals(EMAIL, email.getSender());
        assertEquals(EMAIL_1, email.getRecipient());
        assertEquals(TEST_SUBJECT, email.getSubject());
        assertEquals(TEST_BODY, email.getBody());
        assertEquals(TEST_TIMESTAMP, email.getTimestamp());
    }

    @Test
    void testSetters() {
        Email email = new Email();

        email.setId(TEST_ID);
        email.setSender(EMAIL);
        email.setRecipient(EMAIL_1);
        email.setSubject(TEST_SUBJECT);
        email.setBody(TEST_BODY);
        email.setTimestamp(TEST_TIMESTAMP);

        assertEquals(TEST_ID, email.getId());
        assertEquals(EMAIL, email.getSender());
        assertEquals(EMAIL_1, email.getRecipient());
        assertEquals(TEST_SUBJECT, email.getSubject());
        assertEquals(TEST_BODY, email.getBody());
        assertEquals(TEST_TIMESTAMP, email.getTimestamp());
    }

}
