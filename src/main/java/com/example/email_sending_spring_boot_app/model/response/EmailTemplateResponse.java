package com.example.email_sending_spring_boot_app.model.response;

import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import org.springframework.http.HttpStatus;

public class EmailTemplateResponse {

    private String status;
    private HttpStatus code;
    private EmailRequest data;
    private String message;

    public EmailTemplateResponse() {

    }

    public EmailTemplateResponse(String status, HttpStatus code, EmailRequest data, String message) {
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

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public EmailRequest getData() {
        return data;
    }

    public void setData(EmailRequest data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
