package com.example.email_sending_spring_boot_app.model.response;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class EmailResponse {
    private String status;
    private HttpStatus code;
    private final EmailData data;
    private String message;

    public EmailResponse(String status, HttpStatus code, EmailData data, String message) {
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

    public EmailData getData() {
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

    @Override
    public String toString() {
        return "EmailTemplateResponse{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public static class EmailData {
        String[] toEmail;
        String subject;
        String body;
        String file;

        public EmailData() {
        }

        public EmailData(String[] toEmail, String subject, String body, String file) {
            this.toEmail = toEmail;
            this.subject = subject;
            this.body = body;
            this.file = file;
        }

        public EmailData(String[] toEmail, String subject, String body) {
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

        @Override
        public String toString() {
            return "EmailData{" +
                    "toEmail=" + Arrays.toString(toEmail) +
                    ", subject='" + subject + '\'' +
                    ", body='" + body + '\'' +
                    ", file='" + file + '\'' +
                    '}';
        }
    }
}
