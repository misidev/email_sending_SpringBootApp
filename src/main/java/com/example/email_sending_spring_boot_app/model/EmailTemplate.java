package com.example.email_sending_spring_boot_app.model;

public class EmailTemplate {
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
