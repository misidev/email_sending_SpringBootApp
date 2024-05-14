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

    private static final String[] EMAIL_LIST = new String[]{"milicasimovic77@yahoo.com"};
    private static final String APP_STARTING_SUBJECT = "App is started";
    private static final String APP_SHUTDOWN_SUBJECT = "App is shutting down!";
    private static final String APP_STARTING_BODY = "App is successfully started!";
    private static final String APP_SHUTDOWN_BODY = "Shutdown of the app started!";
    private static final String APP_STARTING = "Email to notify that the application has started running";
    private static final String APP_SHUTDOWN = "Email to notify that the application has started to shut down";

    public static void main(String[] args) {
        SpringApplication.run(EmailSendingSpringBootAppApplication.class, args);
    }

    //Email one or more defined email addresses to notify that the application has started running
    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() {
        senderService.sendSimpleEmail(EMAIL_LIST, APP_STARTING_SUBJECT, APP_STARTING_BODY);
        logger.info(APP_STARTING);
    }

    //Email one or more defined email addresses to notify that the application has started to shut down
    @PreDestroy
    public void triggerMailOnShutdown() {
        senderService.sendSimpleEmail(EMAIL_LIST, APP_SHUTDOWN_SUBJECT, APP_SHUTDOWN_BODY);
        logger.info(APP_SHUTDOWN);
    }

}
