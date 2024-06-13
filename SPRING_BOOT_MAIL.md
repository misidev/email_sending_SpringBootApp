## Configuring Gmail SMTP Server for Sending Emails

To send emails using Gmail's SMTP server, the following properties are configured in application.properties:

- **spring.mail.host**: `smtp.gmail.com`, which is the host address for Gmail's SMTP server.
- **spring.mail.port**: Port `587`, the standard port for secure SMTP (TLS) connections with Gmail.
- **spring.mail.username**: Gmail email address, used for authentication with the SMTP server.
- **spring.mail.password**: Gmail account password (*app password*). Note that storing passwords directly in configuration files
  isn't recommended for security reasons.
- **spring.mail.properties.mail.smtp.auth**: Set to `true` to enable SMTP authentication, required for Gmail's SMTP
  server.
- **spring.mail.properties.mail.smtp.starttls.enable**: Set to `true` to enable STARTTLS, upgrading a plain text
  connection to a secure (TLS) connection.

Replace `<login user to smtp server>` and `<login password to smtp server>` with your actual Gmail username and
password(app password).

#### Additional Steps:

1. **Enable Less Secure Apps Access**: In your Gmail account settings, ensure less secure apps access is enabled.

2. **Two-Factor Authentication**: If using two-factor authentication, generate an app-specific password for your
   application.

3. **Generate app password**: Log in gmail account and go on https://myaccount.google.com/u/0/apppasswords to generate password.

