package com.example.email_sending_spring_boot_app.exception;

import com.example.email_sending_spring_boot_app.model.response.ErrorResponse;

public class UsernameNotFoundException extends RuntimeException {
    private ErrorResponse errorResponse;

    public UsernameNotFoundException(String message, ErrorResponse errorResponse) {
        super(message);
        this.errorResponse = errorResponse;
    }

    public UsernameNotFoundException() {

    }

    public UsernameNotFoundException(String s) {
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
