package com.example.email_sending_spring_boot_app.model;


import com.example.email_sending_spring_boot_app.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.EMAIL;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.TEST_ID;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.TEST_USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
class UserTestSmall {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User(TEST_ID,
                TEST_USERNAME,
                EMAIL);

        assertEquals(TEST_ID, user.getId());
        assertEquals(TEST_USERNAME, user.getUsername());
        assertEquals(EMAIL, user.getEmail());
    }

    @Test
    void testUserSetters() {
        User user = new User(null, null, null);
        user.setId(TEST_ID);
        user.setUsername(TEST_USERNAME);
        user.setEmail(EMAIL);

        assertEquals(TEST_ID, user.getId());
        assertEquals(TEST_USERNAME, user.getUsername());
        assertEquals(EMAIL, user.getEmail());
    }

}
