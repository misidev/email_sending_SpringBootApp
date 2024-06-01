package com.example.email_sending_spring_boot_app.constants;

import java.time.LocalDateTime;

public class ApplicationConstants {

    private ApplicationConstants() {
        throw new AssertionError();
    }

    public static final String SUBJECT_FOR_MAIL_WITH_ATTACHMENT = "User notification with attachment";
    public static final String BODY_FOR_MAIL_WITH_ATTACHMENT = "Post request for user with attachment is triggered!";
    public static final String FILE_FOR_MAIL_WITH_ATTACHMENT = "src/main/resources/images/test.jpg";
    public static final String FILE_NAME = FILE_FOR_MAIL_WITH_ATTACHMENT.substring(FILE_FOR_MAIL_WITH_ATTACHMENT.lastIndexOf("/") + 1);
    public static final String LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT = "POST REQUEST email with attachment.";

    public static final String SUBJECT_FOR_SIMPLE_MAIL = "User notification";
    public static final String BODY_FOR_SIMPLE_MAIL = "Post request for user is triggered!";
    public static final String LOGGER_MESSAGE_FOR_SIMPLE_MAIL = "POST REQUEST simple email.";

    public static final String APP_STARTING_SUBJECT = "App is started";
    public static final String APP_SHUTDOWN_SUBJECT = "App is shutting down!";
    public static final String APP_STARTING_BODY = "App is successfully started!";
    public static final String APP_SHUTDOWN_BODY = "Shutdown of the app started!";
    public static final String APP_STARTING = "Email to notify that the application has started running";
    public static final String APP_SHUTDOWN = "Email to notify that the application has started to shut down";

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILURE = "failure";

    public static final String MESSAGE_UNAUTHORIZED = "Unauthorized";
    public static final String DETAILS_UNAUTHORIZED = "You are not authorized to access this resource.";

    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String DETAILS_INTERNAL_SERVER_ERROR = "An unexpected error occurred while processing your request.";

    public static final String MESSAGE_SERVICE_UNAVAILABLE = "Service Unavailable";
    public static final String DETAILS_SERVICE_UNAVAILABLE = "The server is currently unable to handle the request due to temporary overloading or maintenance of the server.";


    public static final String TEST_EMAIL = "milicasimovic77@yahoo.com";
    public static final String[] TEST_EMAIL_LIST = new String[]{TEST_EMAIL};
    public static final String TEST_EMAIL_1 = "milicasimovic888@yahoo.com";
    public static final String TEST_SUBJECT = "Test subject";
    public static final String TEST_BODY = "Test body";
    public static final String TEST_FILE = "Test file";
    public static final String TEST_ERROR_MESSAGE = "Test error response message";
    public static final String TEST_DETAILS = "Test details of response message";
    public static final Long TEST_ID = 1L;
    public static final Long TEST_ID_2 = 2L;
    public static final String TEST_USERNAME = "Username";

    public static final LocalDateTime TEST_TIMESTAMP = LocalDateTime.now();
    public static final  Integer CODE_OK=206;

    public static final String CODE_OK_RESPONSE = "\"code\":\"OK\",";
    public static final String STATUS_SUCCESS_RESPONSE = "{\"status\":\"success\",";
    public static final String DATA_RESPONSE = "\"data\":";
    public static final String EMAIL_TO_RESPONSE = "{\"toEmail\":[\"milicasimovic77@yahoo.com\"],";
    public static final String FILE_NULL_RESPONSE = "\"file\":null},";
    public static final String TEST_SIMPLE_EMAIL = STATUS_SUCCESS_RESPONSE +
            CODE_OK_RESPONSE +
            DATA_RESPONSE +
            EMAIL_TO_RESPONSE +
            "\"subject\":\"User notification\"," +
            "\"body\":\"Post request for user is triggered!\"," +
            FILE_NULL_RESPONSE +
            "\"message\":\"POST REQUEST simple email.\"}";
    public static final String TEST_EMAIL_WITH_ATTACHMENT = STATUS_SUCCESS_RESPONSE +
            CODE_OK_RESPONSE +
            DATA_RESPONSE +
            EMAIL_TO_RESPONSE +
            "\"subject\":\"User notification with attachment\"," +
            "\"body\":\"Post request for user with attachment is triggered!\"," +
            "\"file\":\"test.jpg\"}," +
            "\"message\":\"POST REQUEST email with attachment.\"}";

    public static final String TEST_SHUTDOWN_EMAIL = STATUS_SUCCESS_RESPONSE +
            CODE_OK_RESPONSE +
            DATA_RESPONSE +
            EMAIL_TO_RESPONSE +
            "\"subject\":\"App is shutting down!\"," +
            "\"body\":\"Shutdown of the app started!\"," +
            FILE_NULL_RESPONSE +
            "\"message\":\"Email to notify that the application has started to shut down\"}";

    public static final String TEST_APP_STARTS_EMAIL = STATUS_SUCCESS_RESPONSE +
            CODE_OK_RESPONSE +
            DATA_RESPONSE +
            EMAIL_TO_RESPONSE +
            "\"subject\":\"App is started\"," +
            "\"body\":\"App is successfully started!\"," +
            FILE_NULL_RESPONSE +
            "\"message\":\"Email to notify that the application has started running\"}";

    public static final String TEST_EMAIL_RESPONSE = STATUS_SUCCESS_RESPONSE +
            CODE_OK_RESPONSE +
            DATA_RESPONSE +
            EMAIL_TO_RESPONSE +
            "\"subject\":\"Test Subject\"," +
            "\"body\":\"Test Body\"," +
            "\"file\":\"test.txt\"}," +
            "\"message\":\"POST REQUEST email with attachment.\"}";

}
