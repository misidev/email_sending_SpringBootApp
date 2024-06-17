package com.example.email_sending_spring_boot_app.model.response;

import com.example.email_sending_spring_boot_app.model.entity.User;
import org.springframework.http.HttpStatus;

import java.util.List;

public class UsersResponse {

    private String status;
    private HttpStatus code;
    private final List<User> user;
    private String message;

    public UsersResponse(String status, HttpStatus code, List<User> user, String message) {
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

    public List<User> getUser() {
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
        return "UsersResponse{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", user=" + user +
                ", message='" + message + '\'' +
                '}';
    }

}
