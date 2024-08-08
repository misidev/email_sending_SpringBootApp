package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmailSenderService {

    @Async("taskExecutor")
    List<Email> getAllEmails();

    @Async("taskExecutor")
    CompletableFuture<EmailResponse> sendEmailWithoutAttachmentAsyncWrapper(String[] toEmail, String subject, String body)
            throws MailAuthenticationException,
            MailPreparationException,
            MailParseException;

    @Async("taskExecutor")
    void sendEmailsTemplate(EmailRequest emailRequest, String template) throws IOException, MessagingException;

    @Async("taskExecutor")
    CompletableFuture<EmailResponse> sendEmailsAppStartsShutdownAsyncWrapper(String[] toEmails,
                                              String subject,
                                              String applicationStatus,
                                              String template,
                                              Date currentDateAndTime,
                                              String signature) throws IOException, DocumentException, MessagingException;

    @Async("taskExecutor")
    CompletableFuture<EmailResponse> sendEmailWithAttachmentAsyncWrapper(String toEmail,
                                    String subject,
                                    String body,
                                    String file,
                                    String emailContent,
                                    String template)
            throws MessagingException,
            IOException,
            MailAuthenticationException,
            MailPreparationException,
            MailParseException;

    @Async("taskExecutor")
    CompletableFuture<EmailResponse> sendAttachedEmailThroughRequestAsyncWrapper(String[] toEmail,
                                                  String subject,
                                                  String body,
                                                  MultipartFile file)
            throws MailParseException,
            MailPreparationException,
            MailAuthenticationException,
            IOException,
            MessagingException;

}
