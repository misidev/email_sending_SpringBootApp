package com.example.email_sending_spring_boot_app.model;

public class ErrorResponse {

    private Error error;

    public ErrorResponse(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public static class Error {
        String status;
        Integer code;
        String message;
        String details;

        public Error(String status, Integer code, String message, String details) {
            this.status = status;
            this.code = code;
            this.message = message;
            this.details = details;
        }


        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
