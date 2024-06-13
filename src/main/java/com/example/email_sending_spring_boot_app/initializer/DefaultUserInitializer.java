package com.example.email_sending_spring_boot_app.initializer;

import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
            User user1 = new User();
            user1.setUsername("Alex");
            user1.setEmail("test.service.user888@gmail.com");

            User user2 = new User();
            user2.setUsername("Alex");
            user2.setEmail("testserviceuser888@yahoo.com");

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}
