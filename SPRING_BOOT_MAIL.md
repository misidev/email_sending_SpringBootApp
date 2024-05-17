## Configuring Gmail SMTP Server for Sending Emails

To send emails using Gmail's SMTP server, configure your application with the following properties:

- **spring.mail.host**: Set to `smtp.gmail.com`, which is the host address for Gmail's SMTP server.
- **spring.mail.port**: Use port `587`, the standard port for secure SMTP (TLS) connections with Gmail.
- **spring.mail.username**: Your Gmail email address, used for authentication with the SMTP server.
- **spring.mail.password**: Your Gmail account password. Note that storing passwords directly in configuration files
  isn't recommended for security reasons.
- **spring.mail.properties.mail.smtp.auth**: Set to `true` to enable SMTP authentication, required for Gmail's SMTP
  server.
- **spring.mail.properties.mail.smtp.starttls.enable**: Set to `true` to enable STARTTLS, upgrading a plain text
  connection to a secure (TLS) connection.

Replace `<login user to smtp server>` and `<login password to smtp server>` with your actual Gmail username and
password.

#### Additional Steps:

1. **Enable Less Secure Apps Access**: In your Gmail account settings, ensure less secure apps access is enabled.

2. **Two-Factor Authentication**: If using two-factor authentication, generate an app-specific password for your
   application.

## Notify Application Start and Shutdown

### Description

These methods trigger the sending of emails to notify one or more defined email addresses that the application is
starting or shutting down.

### Method 1: Notify on Application Start

This method sends an email notification when the application starts running.

- **Event**: `ApplicationReadyEvent`
- **Listener Method**:
  ```java
  @EventListener(ApplicationReadyEvent.class)
  public void triggerMail() {
      senderService.sendSimpleEmail(EMAIL_LIST, APP_STARTING_SUBJECT, APP_STARTING_BODY);
      logger.info(APP_STARTING);
  }

### Method 2: Notify on Application Shutdown

This method sends an email notification when the application starts to shut down.

- **Method**: `@PreDestroy` annotated method
- **Shutdown Hook**:
  ```java
  @PreDestroy
  public void triggerMailOnShutdown() {
      senderService.sendSimpleEmail(EMAIL_LIST, APP_SHUTDOWN_SUBJECT, APP_SHUTDOWN_BODY);
      logger.info(APP_SHUTDOWN);
  }

### Parameters

- **EMAIL_LIST**: List of email addresses to notify.
- **APP_STARTING_SUBJECT, APP_SHUTDOWN_SUBJECT**: Subject of the email notification.
- **APP_STARTING_BODY, APP_SHUTDOWN_BODY**: Body content of the email notification.

## SendMailWithAttachmentController

### Description

This controller handles requests related to sending emails with attachments.

### Endpoints

#### Send Email with Attachment

- **URL**: `/api/mail/sendEmailWithAttachment`
- **Method**: POST
- **Description**: Sends an email with an attachment to the specified user.
- **Request Parameters**:
    - `user` (required): The email address of the recipient.
- **Response**:
    - **Success Response**: Returns an `EmailTemplate` object containing details of the sent email.
    - **Error Response**: Throws `MessagingException` or `IOException` if there are issues with sending the email.
- **Example Request**:
  ```bash
  curl -X POST "http://yourdomain.com/api/mail/sendEmailWithAttachment?user=user@example.com"

- **Example Response**:

  ```json
  {
   "toEmail": [
     "user@example.com"
   ],
   "subject": "Userjkhuh notification with attachment",
   "body": "Get request for user with attachment is triggered!",
   "file": null
  }
  ```

**Dependencies**

- **EmailSenderService**: Service responsible for sending emails with attachments.

**Logger**
  - **Name**: SendMailWithAttachmentController
  - **Description**: Logs information about email sending operations.

## SendSimpleMailController

### Description
This controller handles requests related to sending simple emails.

### Endpoints

#### Send Simple Email
- **URL**: `/api/mail/sendSimpleEmail`
- **Method**: POST
- **Description**: Sends a simple email to the specified user.
- **Request Parameters**:
  - `user` (required): The email address of the recipient.
- **Response**:
  - Returns an `EmailTemplate` object containing details of the sent email.
- **Example Request**:
  ```bash
  curl -X POST "http://yourdomain.com/api/mail/sendSimpleEmail?user=user@example.com"

- **Example Response**:

  ```json
  {
   "toEmail": [
     "user@example.com"
   ],
   "subject": "Userjkhuh notification",
   "body": "Get request for user is triggered!",
   "file": null
  }
  ```

**Dependencies**

- **EmailSenderService**: Service responsible for sending simple emails.

**Logger**
- **Name**: SendSimpleMailController
- **Description**: Logs information about email sending operations.