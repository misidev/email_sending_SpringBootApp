package com.example.email_sending_spring_boot_app.model.response;

import com.example.email_sending_spring_boot_app.model.entity.User;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {

    private String status;
    private HttpStatus code;
    private List<User> user;
    private String message;

}
