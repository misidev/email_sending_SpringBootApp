package com.example.email_sending_spring_boot_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmailSendingSpringBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailSendingSpringBootAppApplication.class, args);
    }

}
