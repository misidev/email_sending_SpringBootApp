package com.example.email_sending_spring_boot_app.exception;

import com.example.email_sending_spring_boot_app.model.response.ErrorResponse;

public class UsernameException extends RuntimeException {
    private ErrorResponse errorResponse;

    public UsernameException(String message, ErrorResponse  errorResponse) {
        super(message);
        this.errorResponse= errorResponse;
    }

    public UsernameException() {

    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
