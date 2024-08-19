package com.example.email_sending_spring_boot_app.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailResponse {
    private String status;
    private HttpStatus code;
    private EmailData data;
    private String message;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EmailData {
        String[] toEmail;
        String subject;
        String body;
        String file;

    }

}
