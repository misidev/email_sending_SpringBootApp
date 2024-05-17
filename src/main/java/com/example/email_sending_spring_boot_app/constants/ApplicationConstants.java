package com.example.email_sending_spring_boot_app.constants;

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
    public static final String DETAILS_UNAUTHORIZED= "You are not authorized to access this resource.";

    public static final String MESSAGE_INTERNAL_SERVER_ERROR= "Internal Server Error";
    public static final String DETAILS_INTERNAL_SERVER_ERROR= "An unexpected error occurred while processing your request.";

    public static final String MESSAGE_SERVICE_UNAVAILABLE= "Service Unavailable";
    public static final String DETAILS_SERVICE_UNAVAILABLE= "The server is currently unable to handle the request due to temporary overloading or maintenance of the server.";

}
