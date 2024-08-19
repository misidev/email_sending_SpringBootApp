package com.example.email_sending_spring_boot_app.util;

import com.example.email_sending_spring_boot_app.constants.ApplicationConstants;
import com.example.email_sending_spring_boot_app.model.entity.Email;
import com.example.email_sending_spring_boot_app.model.entity.User;
import com.example.email_sending_spring_boot_app.model.response.EmailResponse;
import com.example.email_sending_spring_boot_app.model.response.ErrorResponse;
import com.example.email_sending_spring_boot_app.model.response.UserResponse;
import com.example.email_sending_spring_boot_app.model.response.UsersResponse;
import com.example.email_sending_spring_boot_app.repository.EmailRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.email_sending_spring_boot_app.constants.ApplicationConstants.*;
import static com.example.email_sending_spring_boot_app.constants.TestConstants.*;
import static com.example.email_sending_spring_boot_app.controller.SendEmailTemplateController.EMAIL_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
class HandleDbInputAndResponsesTest {
    @InjectMocks
    private HandleDbInputAndResponses handleDbInputAndResponses;

    @Mock
    private EmailRepository emailRepository;

    @BeforeEach
    public void setUp() {
        handleDbInputAndResponses = new HandleDbInputAndResponses();
    }

    @Test
    void addingEmailTest() {
        Email email = Email.builder()
                .id(TEST_ID)
                .sender(EMAIL_1)
                .recipient(EMAIL)
                .subject(TEST_SUBJECT)
                .body(TEST_BODY)
                .timestamp(TEST_TIMESTAMP)
                .build();

        Email emailActual = handleDbInputAndResponses.addingEmail(TEST_SUBJECT,
                TEST_BODY,
                EMAIL,
                EMAIL_1,
                TEST_TIMESTAMP);
        emailActual.setId(TEST_ID);
        assertEquals(email.toString(), emailActual.toString());
    }

    @Test
    void handleUnauthorized() {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .message(MESSAGE_UNAUTHORIZED)
                .details(DETAILS_UNAUTHORIZED + "MailAuthenticationException")
                .build();

        ErrorResponse errorResponseExpected = ErrorResponse.builder().error(error).build();
        ErrorResponse errorResponseActual = handleDbInputAndResponses.handleUnauthorized("MailAuthenticationException");

        assertEquals(errorResponseExpected.toString(), errorResponseActual.toString());
    }

    @Test
    void handleInternalServerError() {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .message(MESSAGE_INTERNAL_SERVER_ERROR)
                .details(DETAILS_INTERNAL_SERVER_ERROR + "UsernameException")
                .build();

        ErrorResponse errorResponseExpected = ErrorResponse.builder().error(error).build();
        ErrorResponse errorResponseActual = handleDbInputAndResponses.handleInternalServerError("UsernameException");

        assertEquals(errorResponseExpected.toString(), errorResponseActual.toString());
    }

    @Test
    void testHandleBadRequest() {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_BAD_REQUEST)
                .message(MESSAGE_BAD_REQUEST)
                .details(DETAILS_BAD_REQUEST + "ConstraintViolationException")
                .build();

        ErrorResponse errorResponseExpected = ErrorResponse.builder().error(error).build();
        ErrorResponse errorResponseActual = handleDbInputAndResponses.handleBadRequest("ConstraintViolationException");

        assertEquals(errorResponseExpected.toString(), errorResponseActual.toString());
    }

    @Test
    void testHandleUsernameNotFound() {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .status(STATUS_FAILURE)
                .code(HttpServletResponse.SC_NOT_FOUND)
                .message(MESSAGE_NOT_FOUND)
                .details(DETAILS_NOT_FOUND + "UsernameException")
                .build();

        ErrorResponse errorResponseExpected = ErrorResponse.builder().error(error).build();
        ErrorResponse errorResponseActual = handleDbInputAndResponses.handleUsernameNotFound("UsernameException");

        assertEquals(errorResponseExpected.toString(), errorResponseActual.toString());
    }

    @Test
    void handleSuccessResponseAppStarts() {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(EMAIL_LIST,
                APP_STARTING_SUBJECT,
                APP_STARTING_BODY,
                SIGNATURE);

        EmailResponse emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                APP_STARTING);

        EmailResponse emailResponseActual = handleDbInputAndResponses.handleSuccessResponseAppStarts();

        assertEquals(emailResponseExpected.toString(), emailResponseActual.toString());
    }

    @Test
    void handleSuccessResponseShutdown() {

        EmailResponse.EmailData emailData = new EmailResponse.EmailData(EMAIL_LIST,
                APP_SHUTDOWN_SUBJECT,
                APP_SHUTDOWN_BODY,
                SIGNATURE);

        EmailResponse emailResponseExpected = new EmailResponse(
                ApplicationConstants.STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                ApplicationConstants.APP_SHUTDOWN);

        EmailResponse emailResponseActual = handleDbInputAndResponses.handleSuccessResponseShutdown();

        assertEquals(emailResponseExpected.toString(), emailResponseActual.toString());
    }

    @Test
    void handleSuccessResponseAttachment() {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{EMAIL}, TEST_SUBJECT, TEST_BODY, TEST_FILE);

        EmailResponse emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        EmailResponse emailResponseActual = handleDbInputAndResponses.handleSuccessResponseAttachment(EMAIL, TEST_SUBJECT, TEST_BODY, TEST_FILE);

        assertEquals(emailResponseExpected.toString(), emailResponseActual.toString());
    }

    @Test
    void handleSuccessResponseSimple() {
        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{EMAIL},
                SUBJECT_FOR_SIMPLE_MAIL,
                BODY_FOR_SIMPLE_MAIL,
                null);

        EmailResponse emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_SIMPLE_MAIL);

        EmailResponse emailResponseActual = handleDbInputAndResponses.handleSuccessResponseSimple(EMAIL);

        assertEquals(emailResponseExpected.toString(), emailResponseActual.toString());
    }

    @Test
    void testHandleSuccessResponseAddUse() {
        User user = User.builder()
                .id(TEST_ID)
                .username("Alex")
                .email("testserviceuser888@yahoo.com")
                .build();

        UserResponse userResponseExpected = new UserResponse(STATUS_SUCCESS,
                HttpStatus.OK,
                user,
                LOGGER_MESSAGE_ADD_USER);

        UserResponse userResponseActual = handleDbInputAndResponses.handleSuccessResponseAddUser(user);

        assertEquals(userResponseExpected.toString(), userResponseActual.toString());
    }

    @Test
    void testHandleSuccessResponseGetUsers() {
        User user = User.builder()
                .id(TEST_ID)
                .username("Alex")
                .email("testserviceuser888@yahoo.com")
                .build();

        List<User> users = List.of(user);

        UsersResponse userResponseExpected = new UsersResponse(STATUS_SUCCESS,
                HttpStatus.OK,
                users,
                LOGGER_MESSAGE_GET_ALL_USERS);

        UsersResponse userResponseActual = handleDbInputAndResponses.handleSuccessResponseGetUsers(users);

        assertEquals(userResponseExpected.toString(), userResponseActual.toString());
    }

    @Test
    void handleSuccessResponseMultipartFile() {
        MockMultipartFile attachment = new MockMultipartFile("attachments", "test.txt", "text/plain", "Attachment Content".getBytes());

        EmailResponse.EmailData emailData = new EmailResponse.EmailData(new String[]{EMAIL},
                TEST_SUBJECT,
                TEST_BODY,
                attachment.getOriginalFilename());

        EmailResponse emailResponseExpected = new EmailResponse(
                STATUS_SUCCESS,
                HttpStatus.OK,
                emailData,
                LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT);

        EmailResponse emailResponseActual = handleDbInputAndResponses.handleSuccessResponseMultipartFile(EMAIL,
                TEST_SUBJECT,
                TEST_BODY,
                attachment);

        assertEquals(emailResponseExpected.toString(), emailResponseActual.toString());
    }

}
