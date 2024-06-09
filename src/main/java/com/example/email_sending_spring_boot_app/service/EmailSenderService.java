package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.model.response.ErrorResponse;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import com.example.email_sending_spring_boot_app.util.HandleDbInputAndResponses;
import com.lowagie.text.DocumentException;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.APP_STARTING_SUBJECT;

@Service
public class EmailSenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);

    private ErrorResponse errorResponse;

    @Value("${spring.mail.username}")
    String mailSenderUsername;

    @Value("${spring.application.name}")
    String applicationName;
    @Autowired
    private JavaMailSender javaMailSender;

    private HttpServletResponse httpServletResponse;
    @Autowired
    HandleDbInputAndResponses handleDBInputAndResponses;

    @Autowired
    private TemplateEngine templateEngine;

    private final Context context = new Context();

    //for adding emails in db
    @Autowired
    private EmailRepository emailRepository;

    private EmailResponse emailResponse = null;


    public EmailResponse sendEmailsWithoutAttachment(String[] toEmail,
                                                     String subject,
                                                     String body
    ) {
        try {
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
        } catch (Exception ex) {
            // Handle mail sending exceptions
            ex.printStackTrace();
            errorResponse = handleDBInputAndResponses.handleInternalServerError();// HTTP 500
        }
        return emailResponse;
    }


    public EmailResponse sendEmailsAppStartsShutdown(String[] toEmails,
                                                     String subject,
                                                     String applicationStatus,
                                                     String template,
                                                     Date currentDateAndTime,
                                                     String signature) throws IOException, DocumentException {

        for (String toEmail : toEmails) {
            // Create a Thymeleaf context for each email
            context.setVariable("user", toEmail);
            context.setVariable("appName", applicationName);
            context.setVariable("appId", "testAppId888");
            context.setVariable("date", currentDateAndTime);
            context.setVariable("environment", "dev");
            context.setVariable("applicationStatus", applicationStatus);

            addSignature(signature);

            String htmlContent = templateEngine.process(template, context);
            String filePdf = handleDBInputAndResponses.generatePdf(applicationName, "testAppId888", applicationStatus, String.valueOf(currentDateAndTime), "dev");

            // Send the email
            emailResponse = sendAttachedEmail(toEmail, subject, null, filePdf, htmlContent, template);
        }

        return emailResponse;
    }

    public void sendEmailsTemplate(EmailRequest emailRequest, String template) throws IOException {
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

            sendAttachedEmail(toEmail, emailRequest.getSubject(), null, null, htmlContent, template);
        }

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


    public EmailResponse sendAttachedEmail(String toEmail,
                                           String subject,
                                           String body,
                                           String file,
                                           String emailContent,
                                           String template) {
        try {
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

            javaMailSender.send(message);

            if (template != null) {
                emailResponse = handleDBInputAndResponses.handleSuccessResponseAttachment(toEmail, subject, body, file);
            } else if (subject.equals(APP_STARTING_SUBJECT)) {
                emailResponse = handleDBInputAndResponses.handleSuccessResponseAppStarts();
            } else {
                emailResponse = handleDBInputAndResponses.handleSuccessResponseShutdown();
            }


            LOGGER.info("Email with attachment is sent from user: {} to {}", mailSenderUsername, toEmail);

            handleDBInputAndResponses.saveEmail(toEmail, subject, template, mailSenderUsername);
        } catch (AuthenticationFailedException ex) {
            // Handle authentication failure
            errorResponse = handleDBInputAndResponses.handleUnauthorized();// HTTP 401
        } catch (MessagingException ex) {
            // Handle other messaging exceptions
            errorResponse = handleDBInputAndResponses.handleInternalServerError();// HTTP 500
        } catch (IOException ex) {
            errorResponse = handleDBInputAndResponses.handleServiceUnavailable();// HTTP 503
        }
        return emailResponse;
    }

    public EmailResponse sendAttachedEmailThroughRequest(String[] toEmail,
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

            if (file != null) {
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

            emailResponse = handleDBInputAndResponses.handleSuccessResponseMultipartFile(Arrays.toString(toEmail), subject, body, file);

            handleDBInputAndResponses.saveEmails(toEmail, subject, body, mailSenderUsername);
        } catch (AuthenticationFailedException ex) {
            // Handle authentication failure
            errorResponse = handleDBInputAndResponses.handleUnauthorized();// HTTP 401
        } catch (MessagingException ex) {
            // Handle other messaging exceptions
            errorResponse = handleDBInputAndResponses.handleInternalServerError(); // HTTP 500
        } catch (IOException ex) {
            // Handle IO exceptions
            errorResponse = handleDBInputAndResponses.handleServiceUnavailable(); // HTTP 503
        }

        return emailResponse;
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

}
