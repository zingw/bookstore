package com.example.book.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {

    @NotBlank(message = "user name can not empty!")
    private String username;

    @NotBlank(message = "password can not empty!")
    private String password;
}
