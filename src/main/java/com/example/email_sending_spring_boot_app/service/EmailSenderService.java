package com.example.email_sending_spring_boot_app.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);
    @Value("${spring.mail.username}")
    String mailSenderUsername;

    public void sendSimpleEmail(String[] toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSenderUsername);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);

        logger.info("Email is sent from user: " + mailSenderUsername + " to " + Arrays.toString(toEmail));
    }

    public Resource sendAttachedEmail(String[] toEmail,
                                      String subject,
                                      String body,
                                      String file
    ) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);

        Path path = Paths.get(file);
        byte[] content = Files.readAllBytes(path);
        Resource attachment = new ByteArrayResource(content);

        mimeMessageHelper.addAttachment("Attachment.jpg", attachment);

        javaMailSender.send(message);
        logger.info("Email with attachment is sent from user: " + mailSenderUsername + " to " + Arrays.toString(toEmail));

        return attachment;
    }

}
