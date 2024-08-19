package com.example.email_sending_spring_boot_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class EmailSendingSpringBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailSendingSpringBootAppApplication.class, args);
    }

}
