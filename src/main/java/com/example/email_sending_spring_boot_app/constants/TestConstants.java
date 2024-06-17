package com.example.email_sending_spring_boot_app.constants;

import java.time.LocalDateTime;

public class TestConstants {

    private TestConstants() {
        throw new AssertionError();
    }

    public static final String TEST_SUBJECT = "Test subject";
    public static final String TEST_BODY = "Test body";
    public static final String TEST_FILE = "Test file";
    public static final String TEST_ERROR_MESSAGE = "Test error response message";
    public static final String TEST_DETAILS = "Test details of response message";
    public static final String TEST_EVENT_NAME = "Mastering Email Communication: Exploring the JavaMail API";
    public static final String TEST_EVENT_DATE = "8. July 2024";
    public static final String TEST_EVENT_TIME = "11:00";
    public static final String TEST_EVENT_LOCATION = "Online";
    public static final String TEST_REGISTRATION_LINK = "event_registration_link.com";
    public static final String TEST_REGISTRATION_NAME = "Alex";
    public static final String TEST_COMPANY_NAME = "misidev";
    public static final String TEST_YOUR_NAME = "Milica";
    public static final String TEST_YOUR_JOB_TITLE = "Software Engineer";
    public static final Long TEST_ID = 1L;
    public static final Long TEST_ID_2 = 2L;
    public static final String TEST_USERNAME = "Username";
    public static final LocalDateTime TEST_TIMESTAMP = LocalDateTime.now();
    public static final Integer CODE_OK = 206;
    public static final String CODE_OK_RESPONSE = "\"code\":\"OK\",";
    public static final String STATUS_SUCCESS_RESPONSE = "{\"status\":\"success\",";
    public static final String DATA_RESPONSE = "\"data\":";
    public static final String EMAIL_TO_RESPONSE = "{\"toEmail\":[\"testserviceuser888@yahoo.com\",\"test.service.user888@gmail.com\"],";
    public static final String FILE_NULL_RESPONSE = "\"file\":null},";
    public static final String TEST_REQUEST_BODY_ADD = "{\"username\":\"Alex\", \"email\":\"testserviceuser888@yahoo.com\"}";
    public static final String TEST_REQUEST_BODY_TEMPLATE =
            "{\n" +
                    "    \"toEmail\":[\"testserviceuser888@yahoo.com\"],\n" +
                    "    \"subject\":\"Knowledge Transfer\",\n" +
                    "    \"eventName\":\"Mastering Email Communication: Exploring the JavaMail API\",\n" +
                    "    \"eventDate\":\"8. July 2024\",\n" +
                    "    \"eventTime\":\"11:00\",\n" +
                    "    \"eventLocation\":\"Online\",\n" +
                    "    \"eventRegistrationLink\":\"event_registration_link.com\",\n" +
                    "    \"recipientName\":\"Alex\",\n" +
                    "    \"companyName\":\"misidev\",\n" +
                    "    \"yourName\":\"Milica\",\n" +
                    "    \"yourJobTitle\":\"Software Engineer\",\n" +
                    "    \"signature\":\"src/main/resources/images/misidev_signature.png\"\n" +
                    "}";
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
            "{\"toEmail\":[\"testserviceuser888@yahoo.com\"]," +
            "\"subject\":\"User notification with attachment\"," +
            "\"body\":\"Post request for user with attachment is triggered!\"," +
            "\"file\":\"src/main/resources/images/misidev_logo.png\"}," +
            "\"message\":\"POST REQUEST email with attachment.\"}";
    public static final String TEST_SHUTDOWN_EMAIL = STATUS_SUCCESS_RESPONSE +
            CODE_OK_RESPONSE +
            DATA_RESPONSE +
            EMAIL_TO_RESPONSE +
            "\"subject\":\"App is shutting down!\"," +
            "\"body\":\"Shutdown of the app started!\"," +
            "\"file\":\"src/main/resources/images/misidev_signature.png\"}," +
            "\"message\":\"Email to notify that the application has started to shut down\"}";
    public static final String TEST_APP_STARTS_EMAIL = STATUS_SUCCESS_RESPONSE +
            CODE_OK_RESPONSE +
            DATA_RESPONSE +
            EMAIL_TO_RESPONSE +
            "\"subject\":\"App is started\"," +
            "\"body\":\"App is successfully started!\"," +
            "\"file\":\"src/main/resources/images/misidev_signature.png\"}," +
            "\"message\":\"Email to notify that the application has started running\"}";

    public static final String TEST_EMAIL_RESPONSE = STATUS_SUCCESS_RESPONSE +
            CODE_OK_RESPONSE +
            DATA_RESPONSE +
            "{\"toEmail\":[\"test.service.user888@gmail.com\"]," +
            "\"subject\":\"Test subject\"," +
            "\"body\":\"Test body\"," +
            "\"file\":\"test.txt\"}," +
            "\"message\":\"POST REQUEST email with attachment.\"}";
}
