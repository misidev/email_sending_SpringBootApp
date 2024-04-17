package com.example.email_sending_spring_boot_app;

import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EmailSendingSpringBootAppApplication {
    @Autowired
    private EmailSenderService senderService;
    private static final Logger logger = LoggerFactory.getLogger(EmailSendingSpringBootAppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EmailSendingSpringBootAppApplication.class, args);
    }

    //Email one or more defined email addresses to notify that the application has started running
    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        senderService.sendSimpleEmail(new String[]{"milicasimovic77@yahoo.com"},
                "App is started",
                "App is successfully started!");
        logger.info("Email to notify that the application has started running");
    }

    //Email one or more defined email addresses to notify that the application has started to shut down
    @PreDestroy
    public void triggerMailOnShutdown() {
        senderService.sendSimpleEmail(new String[]{"milicasimovic77@yahoo.com"}, "App is shutting down!",
                "Shutdown of the app started!");
        logger.info("Email to notify that the application has started to shut down");
    }

}
