package com.example.email_sending_spring_boot_app.constants;

public class ApplicationConstants {

    private ApplicationConstants() {
        throw new AssertionError();
    }
    public static final String EMAIL = "testserviceuser888@yahoo.com";
    public static final String EMAIL_1 = "test.service.user888@gmail.com";
    public static final String FILE_FOR_MAIL_WITH_ATTACHMENT = "src/main/resources/images/misidev_logo.png";
    public static final String SIGNATURE = "src/main/resources/images/misidev_signature.png";
    public static final String FILE_NAME = FILE_FOR_MAIL_WITH_ATTACHMENT.substring(FILE_FOR_MAIL_WITH_ATTACHMENT.lastIndexOf("/") + 1);
    public static final String LOGGER_MESSAGE_FOR_MAIL_WITH_ATTACHMENT = "POST REQUEST email with attachment.";
    public static final String SUBJECT_FOR_SIMPLE_MAIL = "User notification";
    public static final String BODY_FOR_SIMPLE_MAIL = "Post request for user is triggered!";
    public static final String LOGGER_MESSAGE_FOR_SIMPLE_MAIL = "POST REQUEST simple email.";

    public static final String LOGGER_MESSAGE_GET_ALL_USERS = "GET REQUEST for get all users.";
    public static final String LOGGER_MESSAGE_ADD_USER = "POST REQUEST for add user.";
    public static final String APP_STARTING_SUBJECT = "App is started";
    public static final String APP_SHUTDOWN_SUBJECT = "App is shutting down!";
    public static final String APP_STARTING_BODY = "App is successfully started!";
    public static final String APP_SHUTDOWN_BODY = "Shutdown of the app started!";
    public static final String APP_STARTING = "Email to notify that the application has started running";
    public static final String APP_SHUTDOWN = "Email to notify that the application has started to shut down";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILURE = "failure";
    public static final String STATUS_UP = "UP";
    public static final String STATUS_SHUTDOWN = "SHUTDOWN";
    public static final String MESSAGE_UNAUTHORIZED = "Unauthorized";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String MESSAGE_NOT_FOUND = "Not Found";
    public static final String MESSAGE_BAD_REQUEST = "Bad Request";

    public static final String DETAILS_BAD_REQUEST = "The request to prepare the email message was malformed or invalid, caused by exception: ";
    public static final String DETAILS_INTERNAL_SERVER_ERROR = "An unexpected error occurred while processing your request, caused by exception: ";
    public static final String DETAILS_NOT_FOUND = "An unexpected error occurred while interacting with DB, caused by exception: ";
    public static final String DETAILS_UNAUTHORIZED = "You are not authorized to access this resource, caused by exception: ";
    public static final String TEMPLATE_START_SHUTDOWN = "email_app_status.html";
    public static final String USER = "user";
    public static final String SUBJECT = "subject";
    public static final String BODY = "body";
    public static final String FILE = "file";
    public static final String LOGG_EXCEPTION = "\"Error caused by exception : {}\", Status code of exception : {}\"";
    public static final String SUBJECT_FOR_MAIL_WITH_ATTACHMENT = "User notification with attachment";
    public static final String BODY_FOR_MAIL_WITH_ATTACHMENT = "Post request for user with attachment is triggered!";

}
