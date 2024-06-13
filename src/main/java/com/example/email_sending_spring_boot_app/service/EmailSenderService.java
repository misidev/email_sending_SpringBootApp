package com.example.email_sending_spring_boot_app.service;

import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface EmailSenderService {

   List<Email> getAllEmails();

    EmailResponse sendEmailsWithoutAttachment(String[] toEmail, String subject, String body);

    void sendEmailsTemplate(EmailRequest emailRequest, String template) throws IOException, MessagingException;

    EmailResponse sendEmailsAppStartsShutdown(String[] toEmails,
                                              String subject,
                                              String applicationStatus,
                                              String template,
                                              Date currentDateAndTime,
                                              String signature) throws IOException, DocumentException, MessagingException;

    EmailResponse sendAttachedEmail(String toEmail,
                                    String subject,
                                    String body,
                                    String file,
                                    String emailContent,
                                    String template) throws MessagingException, IOException;

    EmailResponse sendAttachedEmailThroughRequest(String[] toEmail,
                                                  String subject,
                                                  String body,
                                                  MultipartFile file) throws MessagingException, IOException;

}
