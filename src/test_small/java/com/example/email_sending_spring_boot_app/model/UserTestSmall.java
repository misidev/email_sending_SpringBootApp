package com.example.email_sending_spring_boot_app.model;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserTestSmall {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User(ApplicationConstants.TEST_ID,
                ApplicationConstants.TEST_USERNAME,
                ApplicationConstants.TEST_EMAIL);

        assertEquals(ApplicationConstants.TEST_ID, user.getId());
        assertEquals(ApplicationConstants.TEST_USERNAME, user.getUsername());
        assertEquals(ApplicationConstants.TEST_EMAIL, user.getEmail());
    }

    @Test
    void testUserSetters() {
        User user = new User(null, null, null);
        user.setId(ApplicationConstants.TEST_ID);
        user.setUsername(ApplicationConstants.TEST_USERNAME);
        user.setEmail(ApplicationConstants.TEST_EMAIL);

        assertEquals(ApplicationConstants.TEST_ID, user.getId());
        assertEquals(ApplicationConstants.TEST_USERNAME, user.getUsername());
        assertEquals(ApplicationConstants.TEST_EMAIL, user.getEmail());
    }

}
