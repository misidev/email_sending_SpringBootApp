package com.example.email_sending_spring_boot_app.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Error error;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        String status;
        Integer code;
        String message;
        String details;

    }
}
