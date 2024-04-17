#Config for MailService

application.properties:
<pre>
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your.email@gmail.com
spring.mail.password=your_gmail_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
</pre>

<b>spring.mail.host:</b> This property specifies the hostname of the mail server that Spring Boot will use to send emails. For example, if you're using Gmail's SMTP server, you would set this to "smtp.gmail.com".

<b>spring.mail.port:</b> This property specifies the port number of the mail server. For most SMTP servers, the default port is 25, but for secure connections (e.g., using SSL or TLS), it could be 465 for SSL or 587 for TLS. For Gmail, you might set this to 587.

<b>spring.mail.username:</b> This property specifies the username or email address that Spring Boot will use to authenticate with the mail server. For example, if you're using Gmail, this would be your Gmail email address.

<b>spring.mail.password:</b> This property specifies the password associated with the email account specified in spring.mail.username. It's important to keep this secure and consider using environment variables or Spring Boot's built-in encryption features for sensitive information like passwords.

<b>spring.mail.properties.mail.smtp.auth:</b> This property specifies whether SMTP authentication should be enabled. It's typically set to "true" to enable authentication with the mail server. This is required for most SMTP servers.

<b>spring.mail.properties.mail.smtp.starttls.enable:</b> This property specifies whether to enable the use of the STARTTLS command (TLS encryption) when connecting to the SMTP server. It's used for securing email communication. For most SMTP servers, including Gmail, this should be set to "true" to enable TLS encryption.
