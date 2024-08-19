package com.example.email_sending_spring_boot_app.model.response;

import com.example.email_sending_spring_boot_app.model.entity.User;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String status;
    private HttpStatus code;
    private User user;
    private String message;

}
