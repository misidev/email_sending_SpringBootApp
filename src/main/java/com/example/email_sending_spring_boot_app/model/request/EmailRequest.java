package com.example.email_sending_spring_boot_app.model.request;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequest {

    private String[] toEmail;
    private String subject;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventRegistrationLink;
    private String recipientName;
    private String companyName;
    private String yourName;
    private String yourJobTitle;
    private String signature;

}
