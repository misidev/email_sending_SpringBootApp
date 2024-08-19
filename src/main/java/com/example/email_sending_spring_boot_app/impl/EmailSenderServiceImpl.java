package com.example.email_sending_spring_boot_app.impl;

import com.example.email_sending_spring_boot_app.exception.UsernameException;
import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import com.example.email_sending_spring_boot_app.service.EmailSenderService;
import com.example.email_sending_spring_boot_app.service.UsersService;
import com.example.email_sending_spring_boot_app.util.HandleDbInputAndResponses;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderServiceImpl.class);

    public static final String DESCRIPTION_APP_STARTING = "This email confirms that everything is up and running smoothly.";
    public static final String DESCRIPTION_APP_SHUTDOWN = "This email confirms that service is stopped running.";
    @Value("${spring.mail.username}")
    String mailSenderUsername;

    @Value("${spring.application.name}")
    String applicationName;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    HandleDbInputAndResponses handleDBInputAndResponses;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private TaskExecutor taskExecutor;

    private final Context context = new Context();

    //for adding emails in db
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UsersService userService;

    private EmailResponse emailResponse = null;

    public EmailResponse sendEmailsWithoutAttachment(String[] toEmail,
                                                     String subject,
                                                     String body
    ) throws MailAuthenticationException, MailPreparationException, MailParseException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSenderUsername);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        String emailAddresses = Arrays.toString(toEmail);
        LOGGER.info("Email is sent from user: {} to {}", mailSenderUsername, emailAddresses);

        emailResponse = handleDBInputAndResponses.handleSuccessResponseSimple(Arrays.toString(toEmail));

        handleDBInputAndResponses.saveEmails(toEmail, subject, body, mailSenderUsername);

        return emailResponse;
    }

    public EmailResponse sendEmailsAppStartsShutdown(String[] toEmails,
                                                     String subject,
                                                     String applicationStatus,
                                                     String template,
                                                     Date currentDateAndTime,
                                                     String signature) throws IOException, DocumentException, MessagingException {

        for (String toEmail : toEmails) {
            String username = findUsername(toEmail);

            // Create a Thymeleaf context for each email
            context.setVariable("user", username);
            context.setVariable("appName", applicationName);
            context.setVariable("appId", "testAppId888");
            context.setVariable("date", currentDateAndTime);
            context.setVariable("environment", "dev");
            context.setVariable("applicationStatus", applicationStatus);

            String status;
            if (subject.equals(APP_STARTING_SUBJECT)) {
                context.setVariable("description", DESCRIPTION_APP_STARTING);
                context.setVariable("status", STATUS_UP);
                status = STATUS_UP;
            } else {
                context.setVariable("description", DESCRIPTION_APP_SHUTDOWN);
                context.setVariable("status", STATUS_SHUTDOWN);
                status = STATUS_SHUTDOWN;
            }

            addSignature(signature);

            String htmlContent = templateEngine.process(template, context);
            String filePdf = handleDBInputAndResponses.generatePdf(applicationName, "testAppId888", status, String.valueOf(currentDateAndTime), "dev");

            // Send the email
            emailResponse = sendAttachedEmail(toEmail, subject, null, filePdf, htmlContent, template);

        }
        return emailResponse;
    }

    public void sendEmailsTemplate(EmailRequest emailRequest, String template) throws IOException, MessagingException {
        for (String toEmail : emailRequest.getToEmail()) {

            context.setVariable("eventName", emailRequest.getEventName());
            context.setVariable("eventDate", emailRequest.getEventDate());
            context.setVariable("eventTime", emailRequest.getEventTime());
            context.setVariable("eventLocation", emailRequest.getEventLocation());
            context.setVariable("eventRegistrationLink", emailRequest.getEventRegistrationLink());
            context.setVariable("recipientName", emailRequest.getRecipientName());
            context.setVariable("companyName", emailRequest.getCompanyName());
            context.setVariable("yourName", emailRequest.getYourName());
            context.setVariable("yourJobTitle", emailRequest.getYourJobTitle());

            addSignature(emailRequest.getSignature());

            String htmlContent = templateEngine.process(template, context);

            emailResponse = sendAttachedEmail(toEmail, emailRequest.getSubject(), null, null, htmlContent, template);
        }
    }

    public EmailResponse sendAttachedEmail(String toEmail,
                                           String subject,
                                           String body,
                                           String file,
                                           String emailContent,
                                           String template) throws MessagingException,
            IOException,
            MailAuthenticationException,
            MailPreparationException,
            MailParseException {


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);

        if (body != null) {
            mimeMessageHelper.setText(body);
        }

        if (emailContent != null) {
            mimeMessageHelper.setText(emailContent, true);
        }

        if (file != null) {
            Path path = Paths.get(file);
            byte[] content = Files.readAllBytes(path);
            Resource attachment = new ByteArrayResource(content);

            String attachmentName = file.substring(file.lastIndexOf("/") + 1);

            mimeMessageHelper.addAttachment(attachmentName, attachment);
        }
        if (validEmail(toEmail)) {
            javaMailSender.send(message);
        } else {
            throw new MessagingException();
        }

        if (subject.equals(APP_STARTING_SUBJECT)) {
            emailResponse = handleDBInputAndResponses.handleSuccessResponseAppStarts();
        } else if (subject.equals(APP_SHUTDOWN_SUBJECT)) {
            emailResponse = handleDBInputAndResponses.handleSuccessResponseShutdown();
        } else {
            emailResponse = handleDBInputAndResponses.handleSuccessResponseAttachment(toEmail, subject, body, file);
        }

        LOGGER.info("Email with attachment is sent from user: {} to {}", mailSenderUsername, toEmail);

        handleDBInputAndResponses.saveEmail(toEmail, subject, template, mailSenderUsername);

        return emailResponse;
    }

    public EmailResponse sendAttachedEmailThroughRequest(String[] toEmail,
                                                         String subject,
                                                         String body,
                                                         MultipartFile file
    ) throws MailParseException,
            MailPreparationException,
            MailAuthenticationException,
            IOException,
            MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);

        if (file != null) {
            LOGGER.info("Attachment Name: {},  Attachment Content Type {}: ", file.getOriginalFilename(), file.getContentType());
            byte[] content = file.getBytes();
            Resource attachment = new ByteArrayResource(content);

            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), attachment);
        } else {
            LOGGER.info("No attachment provided.");
        }

        javaMailSender.send(message);
        String emailAddresses = Arrays.toString(toEmail);
        LOGGER.info("Email with attachment is sent from user: {} to {}", mailSenderUsername, emailAddresses);

        emailResponse = handleDBInputAndResponses.handleSuccessResponseMultipartFile(Arrays.toString(toEmail), subject, body, file);

        handleDBInputAndResponses.saveEmails(toEmail, subject, body, mailSenderUsername);

        return emailResponse;

    }

    public CompletableFuture<EmailResponse> sendEmailWithoutAttachmentAsyncWrapper(String[] toEmail,
                                                                                   String subject,
                                                                                   String body) throws MailAuthenticationException,
            MailPreparationException,
            MailParseException {
        return CompletableFuture.supplyAsync(() -> sendEmailsWithoutAttachment(toEmail, subject, body), taskExecutor);
    }

    public CompletableFuture<EmailResponse> sendEmailWithAttachmentAsyncWrapper(String toEmail,
                                                                                String subject,
                                                                                String body,
                                                                                String file,
                                                                                String emailContent,
                                                                                String template) throws
            MailAuthenticationException,
            MailPreparationException,
            MailParseException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return sendAttachedEmail(toEmail,
                        subject,
                        body,
                        file,
                        emailContent,
                        template);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<EmailResponse> sendEmailsAppStartsShutdownAsyncWrapper(String[] toEmails,
                                                                                    String subject,
                                                                                    String applicationStatus,
                                                                                    String template,
                                                                                    Date currentDateAndTime,
                                                                                    String signature) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return sendEmailsAppStartsShutdown(toEmails,
                        subject,
                        applicationStatus,
                        template,
                        currentDateAndTime,
                        signature);
            } catch (MessagingException | IOException | DocumentException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<EmailResponse> sendAttachedEmailThroughRequestAsyncWrapper(String[] toEmail,
                                                                                        String subject,
                                                                                        String body,
                                                                                        MultipartFile file) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return sendAttachedEmailThroughRequest(toEmail,
                        subject,
                        body, file);
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    public void addSignature(String signature) throws IOException {
        if (signature != null) {
            Path path = Paths.get(signature);
            byte[] content = Files.readAllBytes(path);

            String base64Content = Base64.getEncoder().encodeToString(content);
            String dataUrl = "data:image/png;base64," + base64Content;

            context.setVariable("signature", dataUrl);
        }
    }

    public String findUsername(String email) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getEmail().equals(email)) {
            return user.getUsername();
        } else {
            throw new UsernameException();
        }
    }

    public boolean validEmail(String email) {
        String emailRegex = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

}
