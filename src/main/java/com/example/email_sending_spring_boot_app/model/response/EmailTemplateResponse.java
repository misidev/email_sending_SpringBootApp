package com.example.email_sending_spring_boot_app.model.response;

import com.example.email_sending_spring_boot_app.model.request.EmailRequest;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailTemplateResponse {

    private String status;
    private HttpStatus code;
    private EmailRequest data;
    private String message;

}
