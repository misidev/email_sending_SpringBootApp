package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.Email;
import com.example.email_sending_spring_boot_app.model.ErrorResponse;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class EmailSenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);

    private ErrorResponse errorResponse;

    @Value("${spring.mail.username}")
    String mailSenderUsername;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    private HttpServletResponse httpServletResponse;

    public void sendSimpleEmail(String[] toEmail,
                                String subject,
                                String body
    ) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailSenderUsername);
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            javaMailSender.send(message);

            String emailAddresses = Arrays.toString(toEmail);
            LOGGER.info("Email is sent from user: {} to {}", mailSenderUsername, emailAddresses);

            saveEmail(toEmail, subject, body, mailSenderUsername);

        } catch (Exception ex) {
            // Handle mail sending exceptions
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // HTTP 500
            errorResponse = handleInternalServerError();
        }
    }

    public void sendAttachedEmail(String[] toEmail,
                                  String subject,
                                  String body,
                                  String file
    ) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            Path path = Paths.get(String.valueOf(file));
            byte[] content = Files.readAllBytes(path);
            Resource attachment = new ByteArrayResource(content);

            mimeMessageHelper.addAttachment(ApplicationConstants.FILE_NAME, attachment);


            javaMailSender.send(message);

            String emailAddresses = Arrays.toString(toEmail);
            LOGGER.info("Email with attachment is sent from user: {} to {}", mailSenderUsername, emailAddresses);

            saveEmail(toEmail, subject, body, mailSenderUsername);

        } catch (AuthenticationFailedException ex) {
            // Handle authentication failure
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401
            errorResponse = handleUnauthorized();
        } catch (MessagingException ex) {
            // Handle other messaging exceptions
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // HTTP 500
            errorResponse = handleInternalServerError();
        } catch (IOException ex) {
            // Handle IO exceptions
            httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE); // HTTP 503
            errorResponse = handleServiceUnavailable();
        }
    }

    public void sendAttachedEmailThroughRequest(String[] toEmail,
                                                String subject,
                                                String body,
                                                MultipartFile file
    ) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            if (!file.isEmpty()) {
                LOGGER.info("Attachment Name: {},  Attachment Content Type {}: ", file.getOriginalFilename(), file.getContentType());
                byte[] content = file.getBytes();
                Resource attachment = new ByteArrayResource(content);

                mimeMessageHelper.addAttachment(file.getOriginalFilename(), attachment);
            } else {
                LOGGER.info("No attachment provided.");
            }

            javaMailSender.send(message);
            String emailAddresses = Arrays.toString(toEmail);
            LOGGER.info("Email with attachment is sent from user: {} to {}", mailSenderUsername, emailAddresses);

            saveEmail(toEmail, subject, body, mailSenderUsername);

        } catch (
                AuthenticationFailedException ex) {
            // Handle authentication failure
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401
            errorResponse = handleUnauthorized();
        } catch (
                MessagingException ex) {
            // Handle other messaging exceptions
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // HTTP 500
            errorResponse = handleInternalServerError();
        } catch (IOException ex) {
            // Handle IO exceptions
            httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE); // HTTP 503
            errorResponse = handleServiceUnavailable();
        }

    }

    public static Email addingEmail(String subject, String body, String recipient, String sender, LocalDateTime timestamp) {
        Email email = new Email();
        email.setSubject(subject);
        email.setBody(body);
        email.setRecipient(recipient);
        email.setSender(sender);
        email.setTimestamp(timestamp);
        return email;
    }

    public void saveEmail(String[] toEmail, String subject, String body, String sender) {
        try {
            for (String email : toEmail) {
                Email emailForDb = addingEmail(subject, body, email, sender, LocalDateTime.now());
                emailRepository.save(emailForDb);
                LOGGER.info("Email with attachment is added in db | toEmail {}, subject {}, body {} and sender {}", toEmail, subject, body, sender);
            }
        } catch (DataAccessException | ConstraintViolationException | TransactionSystemException ex) {
            ex.printStackTrace();
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
            LOGGER.info("Internal Server Error while adding Email with attachment in db");
        }

    }

    private ErrorResponse handleUnauthorized() {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse.Error error = new ErrorResponse.Error(
                ApplicationConstants.STATUS_FAILURE,
                HttpServletResponse.SC_UNAUTHORIZED,
                ApplicationConstants.MESSAGE_UNAUTHORIZED,
                ApplicationConstants.DETAILS_UNAUTHORIZED);

        errorResponse = new ErrorResponse(error);
        return errorResponse;
    }

    private ErrorResponse handleInternalServerError() {
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ErrorResponse.Error error = new ErrorResponse.Error(
                ApplicationConstants.STATUS_FAILURE,
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                ApplicationConstants.MESSAGE_INTERNAL_SERVER_ERROR,
                ApplicationConstants.DETAILS_INTERNAL_SERVER_ERROR);

        errorResponse = new ErrorResponse(error);
        return errorResponse;
    }

    private ErrorResponse handleServiceUnavailable() {
        httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        ErrorResponse.Error error = new ErrorResponse.Error(
                ApplicationConstants.STATUS_FAILURE,
                HttpServletResponse.SC_SERVICE_UNAVAILABLE,
                ApplicationConstants.MESSAGE_SERVICE_UNAVAILABLE,
                ApplicationConstants.DETAILS_SERVICE_UNAVAILABLE);

        errorResponse = new ErrorResponse(error);
        return errorResponse;
    }

}
