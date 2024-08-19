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

    public static final String DETAILS_SERVICE_UNAVAILABLE = "The server is currently unable to handle the request due to temporary " +
            "overloading or maintenance of the server, caused by exception: ";

    public static final String MESSAGE_SERVICE_UNAVAILABLE = "Service Unavailable";


    public Email addingEmail(String subject, String body, String recipient, String sender, LocalDateTime timestamp) {
        return Email.builder()
                .subject(subject)
                .body(body)
                .recipient(recipient)
                .sender(sender)
                .timestamp(timestamp)
                .build();
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

    //generation of .pdf file describing the status of the service(UP,DOWN) for e-mail attachments
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

    //handle success(200 OK) - sending simple email without attachment
    public EmailResponse handleSuccessResponseSimple(String user) {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(new String[]{user})
                .subject(SUBJECT_FOR_SIMPLE_MAIL)
                .body(BODY_FOR_SIMPLE_MAIL)
                .file(null)
                .build();

        emailResponse = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(LOGGER_MESSAGE_FOR_SIMPLE_MAIL)
                .build();

        LOGGER.info("Email response for /sendEmailWithoutAttachment endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }


    public EmailResponse handleSuccessResponseAttachment(String user, String subject, String body, String file) {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(new String[]{user})
                .subject(subject)
                .body(body)
                .file(file)
                .build();

        emailResponse = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT)
                .build();

        LOGGER.info("Email response for /sendEmailWithAttachment endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }


    public EmailResponse handleSuccessResponseMultipartFile(String user, String subject, String body, MultipartFile attachments) {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(new String[]{user})
                .subject(subject)
                .body(body)
                .file(attachments.getOriginalFilename())
                .build();

        emailResponse = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT)
                .build();

        LOGGER.info("Email response for /sendEmail endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }

    public UserResponse handleSuccessResponseAddUser(User user) {
        UserResponse userResponse = UserResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .user(user)
                .message(LOGGER_MESSAGE_ADD_USER)
                .build();

        LOGGER.info("Email response for /add endpoint - 200 OK: {}", userResponse);
        return userResponse;
    }

    public UsersResponse handleSuccessResponseGetUsers(List<User> users) {
        UsersResponse usersResponse = UsersResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .user(users)
                .message(LOGGER_MESSAGE_GET_ALL_USERS)
                .build();

        LOGGER.info("Email response for /add endpoint - 200 OK: {}", usersResponse);
        return usersResponse;
    }

    // Handle authentication failure
    public ErrorResponse handleUnauthorized(String exception) {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .message(MESSAGE_UNAUTHORIZED)
                .details(DETAILS_UNAUTHORIZED + exception)
                .build();

        errorResponse = ErrorResponse.builder().error(error).build();

        LOGGER.info("Error response for 401 Unauthorized: {}", errorResponse);
        return errorResponse;
    }

    // Handle other messaging exceptions
    public ErrorResponse handleInternalServerError(String exception) {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .message(MESSAGE_INTERNAL_SERVER_ERROR)
                .details(DETAILS_INTERNAL_SERVER_ERROR + exception)
                .build();

        errorResponse = ErrorResponse.builder().error(error).build();

        LOGGER.info("Error response for 500 Internal Server Error: {}", errorResponse);
        return errorResponse;
    }

    // user not found in DB
    public ErrorResponse handleUsernameNotFound(String exception) {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_NOT_FOUND)
                .message(MESSAGE_NOT_FOUND)
                .details(DETAILS_NOT_FOUND + exception)
                .build();

        errorResponse = ErrorResponse.builder().error(error).build();

        LOGGER.info("Error response for 404 Not Found: {}", errorResponse);
        return errorResponse;
    }

    public ErrorResponse handleServiceUnavailable(String exception) {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_SERVICE_UNAVAILABLE)
                .message(MESSAGE_SERVICE_UNAVAILABLE)
                .details(DETAILS_SERVICE_UNAVAILABLE + exception)
                .build();

        errorResponse = ErrorResponse.builder().error(error).build();

        LOGGER.info("Error response for 503 Service Unavailable: {}", errorResponse);
        return errorResponse;
    }

    //bad request
    public ErrorResponse handleBadRequest(String exception) {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_BAD_REQUEST)
                .message(MESSAGE_BAD_REQUEST)
                .details(DETAILS_BAD_REQUEST + exception)
                .build();

        errorResponse = ErrorResponse.builder().error(error).build();

        LOGGER.info("Error response for 400 Bad Request: {}", errorResponse);
        return errorResponse;
    }

    //handle bad request, bad input of params for request
    public EmailResponse handleSuccessResponseAppStarts() {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(EMAIL_LIST)
                .subject(APP_STARTING_SUBJECT)
                .body(APP_STARTING_BODY)
                .file(SIGNATURE)
                .build();

        emailResponse = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(APP_STARTING)
                .build();

        LOGGER.info("Email response for /sendAppStartEmail endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }

    public EmailResponse handleSuccessResponseShutdown() {
        EmailResponse.EmailData emailData = EmailResponse.EmailData.builder()
                .toEmail(EMAIL_LIST)
                .subject(APP_SHUTDOWN_SUBJECT)
                .body(APP_SHUTDOWN_BODY)
                .file(SIGNATURE)
                .build();

        emailResponse = EmailResponse.builder()
                .status(STATUS_SUCCESS)
                .code(HttpStatus.OK)
                .data(emailData)
                .message(ApplicationConstants.APP_SHUTDOWN)
                .build();

        LOGGER.info("Email response for /sendShutdownEmail endpoint - 200 OK: {}", emailResponse);
        return emailResponse;
    }


}
