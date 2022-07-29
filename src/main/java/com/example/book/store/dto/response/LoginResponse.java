package com.example.book.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String message;
    private String token;
    private HttpStatus status;

    public LoginResponse(String message){
        this.message = message;
    }
    public LoginResponse(String message,
                         HttpStatus httpStatus){
        this.message = message;
        this.status = httpStatus;
    }
}

