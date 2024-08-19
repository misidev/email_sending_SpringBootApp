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
        User user = User.builder()
                .id(TEST_ID)
                .username(TEST_USERNAME)
                .email(EMAIL)
                .build();

        assertEquals(TEST_ID, user.getId());
        assertEquals(TEST_USERNAME, user.getUsername());
        assertEquals(EMAIL, user.getEmail());
    }

    @Test
    void testUserSetters() {
        User user = User.builder().build();
        user.setId(TEST_ID);
        user.setUsername(TEST_USERNAME);
        user.setEmail(EMAIL);

        assertEquals(TEST_ID, user.getId());
        assertEquals(TEST_USERNAME, user.getUsername());
        assertEquals(EMAIL, user.getEmail());
    }

}
