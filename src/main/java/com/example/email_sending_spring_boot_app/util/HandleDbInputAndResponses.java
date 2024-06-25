package com.example.email_sending_spring_boot_app.util;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.model.response.ErrorResponse;
import com.example.email_sending_spring_boot_app.model.response.UserResponse;
import com.example.email_sending_spring_boot_app.model.response.UsersResponse;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.multipart.MultipartFile;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static com.example.email_sending_spring_boot_app.controller.SendEmailTemplateController.EMAIL_LIST;

@Component
public class HandleDbInputAndResponses {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandleDbInputAndResponses.class);
    private ErrorResponse errorResponse;
    @Autowired
    private EmailRepository emailRepository;

    private EmailResponse emailResponse = null;

    public Email addingEmail(String subject, String body, String recipient, String sender, LocalDateTime timestamp) {
        Email email = new Email();
        email.setSubject(subject);
        email.setBody(body);
        email.setRecipient(recipient);
        email.setSender(sender);
        email.setTimestamp(timestamp);
        return email;
    }

    public void saveEmails(String[] toEmail, String subject, String body, String sender) {
        try {
            for (String email : toEmail) {
                Email emailForDb = addingEmail(subject, body, email, sender, LocalDateTime.now());
                emailRepository.save(emailForDb);
                LOGGER.info("Email with attachment is added in db | toEmail {}, subject {}, body {} and sender {}", toEmail, subject, body, sender);
            }
        } catch (DataAccessException | ConstraintViolationException | TransactionSystemException ex) {
            LOGGER.info("Internal Server Error while adding Email with attachment in db");
            throw ex;
        }

    }

    public void saveEmail(String toEmail, String subject, String body, String sender) {
        try {
            Email emailForDb = addingEmail(subject, body, toEmail, sender, LocalDateTime.now());
            emailRepository.save(emailForDb);
            LOGGER.info("Email with attachment is added in db | toEmail {}, subject {}, body {} and sender {}", toEmail, subject, body, sender);

        } catch (DataAccessException | ConstraintViolationException | TransactionSystemException ex) {
            LOGGER.info("Internal Server Error while adding Email with attachment in db");
            throw ex;
        }

    }

    public String generatePdf(String appName, String appId, String status, String date, String environment) throws DocumentException, IOException {
        String listClosingTag = "</li>\n";
        String htmlContent = "<html><body><h1>Application status:</h1>\n" +
                "<ul>\n" +
                "    <li><strong>Application Name:</strong> " + appName + listClosingTag +
                "    <li><strong>Application ID:</strong> " + appId + listClosingTag +
                "    <li><strong>Application status:</strong> " + status + listClosingTag +
                "    <li><strong>Start Date and time:</strong> " + date + listClosingTag +
                "    <li><strong>Environment:</strong> " + environment + listClosingTag +
                "</ul></body></html>";

        String pdfPath = "src/main/resources/static/app_status.pdf";

        // Generate PDF
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        String filePdf;
        try (OutputStream outputStream = new FileOutputStream(pdfPath)) {
            renderer.createPDF(outputStream);
            filePdf = pdfPath;

            LOGGER.info("File app_status.pdf is generated on path {}", pdfPath);
        } catch (IOException ex) {
            LOGGER.info("Error while generating .pdf file");
            throw ex;
        }
        return filePdf;
    }

    // Handle authentication failure
    public ErrorResponse handleUnauthorized(String exception) {
        ErrorResponse.Error error = new ErrorResponse.Error(
                STATUS_FAILURE,
                HttpServletResponse.SC_UNAUTHORIZED,
                MESSAGE_UNAUTHORIZED,
                DETAILS_UNAUTHORIZED + exception);

        errorResponse = new ErrorResponse(error);

        LOGGER.info("Error response for 401 Unauthorized: {}", errorResponse);
        return errorResponse;
    }

    // Handle other messaging exceptions
    public ErrorResponse handleInternalServerError(String exception) {
        ErrorResponse.Error error = new ErrorResponse.Error(
                STATUS_FAILURE,
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                MESSAGE_INTERNAL_SERVER_ERROR,
                DETAILS_INTERNAL_SERVER_ERROR + exception);

        errorResponse = new ErrorResponse(error);

        LOGGER.info("Error response for 500 Internal Server Error: {}", errorResponse);
        return errorResponse;
    }

    // user not found in DB
    public ErrorResponse handleUsernameNotFound(String exception) {
        ErrorResponse.Error error = new ErrorResponse.Error(
                STATUS_FAILURE,
                HttpServletResponse.SC_NOT_FOUND,
                MESSAGE_NOT_FOUND,
                DETAILS_NOT_FOUND + exception);

        errorResponse = new ErrorResponse(error);

        LOGGER.info("Error response for 404 Not Found: {}", errorResponse);
        return errorResponse;
    }

    public ErrorResponse handleServiceUnavailable(String exception) {
        ErrorResponse.Error error = new ErrorResponse.Error(
                STATUS_FAILURE,
                HttpServletResponse.SC_SERVICE_UNAVAILABLE,
                MESSAGE_SERVICE_UNAVAILABLE,
                DETAILS_SERVICE_UNAVAILABLE + exception);

        errorResponse = new ErrorResponse(error);

        LOGGER.info("Error response for 503 Service Unavailable: {}", errorResponse);
        return errorResponse;
    }

    //bad request
    public ErrorResponse handleBadRequest(String exception) {
        ErrorResponse.Error error = new ErrorResponse.Error(
                STATUS_FAILURE,
                HttpServletResponse.SC_BAD_REQUEST,
                MESSAGE_BAD_REQUEST,
                DETAILS_BAD_REQUEST + exception);

        errorResponse = new ErrorResponse(error);

        LOGGER.info("Error response for 400 Bad Request: {}", errorResponse);
        return errorResponse;
    }

    //handle bad request, bad input of params for request
    public EmailResponse handleSuccessResponseAppStarts() {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(EMAIL_LIST,
                APP_STARTING_SUBJECT,
                APP_STARTING_BODY,
                SIGNATURE);

        emailResponse = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                APP_STARTING);

        LOGGER.info("Email response for /sendAppStartEmail endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }

    public EmailResponse handleSuccessResponseShutdown() {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(EMAIL_LIST,
                APP_SHUTDOWN_SUBJECT,
                APP_SHUTDOWN_BODY,
                SIGNATURE);

        emailResponse = new EmailResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                ApplicationConstants.APP_SHUTDOWN);

        LOGGER.info("Email response for /sendShutdownEmail endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }

    public EmailResponse handleSuccessResponseAttachment(String user, String subject, String body, String file) {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{user}, subject, body, file);

        emailResponse = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        LOGGER.info("Email response for /sendEmailWithAttachment endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }

    public EmailResponse handleSuccessResponseSimple(String user) {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{user},
                SUBJECT_FOR_SIMPLE_MAIL,
                BODY_FOR_SIMPLE_MAIL,null);

        emailResponse = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_SIMPLE_MAIL);

        LOGGER.info("Email response for /sendEmailWithoutAttachment endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }

    public EmailResponse handleSuccessResponseMultipartFile(String user, String subject, String body, MultipartFile attachments) {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{user},
                subject,
                body,
                attachments.getOriginalFilename());

        emailResponse = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        LOGGER.info("Email response for /sendEmail endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }

    public UserResponse handleSuccessResponseAddUser(User user) {
        UserResponse userResponse = new UserResponse(STATUS_SUCCESS,
                HttpStatus.OK,
                user,
                LOGGER_MESSAGE_ADD_USER);

        LOGGER.info("Email response for /add endpoint - 200 OK: {}", userResponse);
        return userResponse;
    }

    public UsersResponse handleSuccessResponseGetUsers(List<User> users) {
        UsersResponse usersResponse = new UsersResponse(STATUS_SUCCESS,
                HttpStatus.OK,
                users,
                LOGGER_MESSAGE_GET_ALL_USERS);

        LOGGER.info("Email response for /add endpoint - 200 OK: {}", usersResponse);
        return usersResponse;
    }

}
