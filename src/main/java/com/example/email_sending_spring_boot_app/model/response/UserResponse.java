package com.example.email_sending_spring_boot_app.model.response;

import com.example.email_sending_spring_boot_app.model.entity.User;
import org.springframework.http.HttpStatus;

public class UserResponse {

    private String status;
    private HttpStatus code;
    private final User user;
    private String message;

    public UserResponse(String status, HttpStatus code, User user, String message) {
        this.status = status;
        this.code = code;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", user=" + user +
                ", message='" + message + '\'' +
                '}';
    }
}
