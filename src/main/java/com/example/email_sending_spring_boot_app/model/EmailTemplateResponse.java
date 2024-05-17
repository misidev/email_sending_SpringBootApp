package com.example.email_sending_spring_boot_app.model;

import org.springframework.http.HttpStatus;

public class EmailTemplateResponse {
    private String status;
    private HttpStatus code;
    private final EmailTemplate data;
    private String message;

    public EmailTemplateResponse(String status, HttpStatus code, EmailTemplate data, String message) {
        this.status = status;
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmailTemplate getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public static class EmailTemplate {
        String[] toEmail;
        String subject;
        String body;
        String file;

        public EmailTemplate() {
        }

        public EmailTemplate(String[] toEmail, String subject, String body, String file) {
            this.toEmail = toEmail;
            this.subject = subject;
            this.body = body;
            this.file = file;
        }

        public EmailTemplate(String[] toEmail, String subject, String body) {
            this.toEmail = toEmail;
            this.subject = subject;
            this.body = body;
        }

        public String[] getToEmail() {
            return toEmail;
        }

        public void setToEmail(String[] toEmail) {
            this.toEmail = toEmail;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

    }
}
