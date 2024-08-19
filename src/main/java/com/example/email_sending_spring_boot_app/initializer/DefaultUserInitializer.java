package com.example.email_sending_spring_boot_app.initializer;

import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.EMAIL;
import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.EMAIL_1;

@Component
public class DefaultUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DefaultUserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if there are any users in the database
        if (userRepository.count() == 0) {
            // Add default users if the database is empty
            String username = "Alex";

            User user1 = User.builder()
                    .username(username)
                    .email(EMAIL)
                    .build();

            User user2 = User.builder()
                    .username(username)
                    .email(EMAIL_1)
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }

}
