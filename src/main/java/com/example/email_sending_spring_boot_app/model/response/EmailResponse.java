package com.example.email_sending_spring_boot_app.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
    public static class EmailData {
        String[] toEmail;
        String subject;
        String body;
        String file;

    }

}
