//package com.example.email_sending_spring_boot_app.service;
//
//import jakarta.mail.internet.MimeMessage;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mock;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import static org.hamcrest.Matchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class EmailSenderServiceTest {
//
//    @Mock
//    private JavaMailSender javaMailSender;
//
//    @Mock
//    private EmailSenderService emailSenderService;
//
//    @Test
//    public void testSendSimpleEmail() {
//        emailSenderService = new EmailSenderService();
////        emailSenderService.setJavaMailSender(javaMailSender);
//
//        // Test data
//        String[] expectedToEmail = {"recipient@example.com"};
//        String expectedSubject = "Test Subject";
//        String expectedBody = "Test Body";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("test");
//        message.setTo(expectedToEmail);
//        message.setText(expectedBody);
//        message.setSubject(expectedSubject);
//         javaMailSender.send(message);
//
//        // Call the sendSimpleEmail method
//        emailSenderService.sendSimpleEmail(expectedToEmail, expectedSubject, expectedBody);
//        when(javaMailSender.send((MimeMessage) ArgumentMatchers.any())).then(javaMailSender.send(message));
//
//        // Verify that javaMailSender.send is called with the correct SimpleMailMessage
//        verify(javaMailSender).send((MimeMessage) any(SimpleMailMessage.class));
//    }
//
////    @Test
////    public void testSendAttachedEmail() throws MessagingException, IOException {
////        // Initialize mocks
////        MockitoAnnotations.initMocks(this);
////
////        // Create instance of EmailService with mock JavaMailSender injected
////        EmailSenderService emailService = new EmailSenderService();
////        emailService.sendSimpleEmail(javaMailSender);
////
////        // Test data
////        String[] expectedToEmail = {"recipient@example.com"};
////        String expectedSubject = "Test Subject";
////        String expectedBody = "Test Body";
////        String expectedFile = "path/to/file.jpg";
////
////        // Call the sendAttachedEmail method
////        Resource expectedAttachment = emailService.sendAttachedEmail(expectedToEmail, expectedSubject, expectedBody, expectedFile);
////
////        // Verify the attachment
////        assertNotNull(expectedAttachment, "Attachment should not be null");
////
////        // Verify that the MimeMessage is created with the correct values
////        ArgumentCaptor<MimeMessage> mimeMessageCaptor = ArgumentCaptor.forClass(MimeMessage.class);
////        verify(javaMailSender).send(mimeMessageCaptor.capture());
////        MimeMessage sentMimeMessage = mimeMessageCaptor.getValue();
////        assertEquals(expectedSubject, sentMimeMessage.getSubject(), "Subject should match");
////        assertEquals(expectedBody, sentMimeMessage.getContent(), "Body should match");
////    }
//}