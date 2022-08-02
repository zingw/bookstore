package com.example.book.store.controller;

import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.reqlogin.LoginReq;
import com.example.book.store.dto.response.LoginResponse;
import com.example.book.store.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject<LoginResponse>> login(@Valid
                               @RequestBody LoginReq req) throws JsonProcessingException {

        ResponseObject<LoginResponse> res = loginService.checkLogin(req);
        return new ResponseEntity<>(res,res.getStatus());
    }
    //forgot password
    @GetMapping("/forgot-password")
    public ResponseEntity<ResponseObject<String>> forgotPassword(@Valid
                                                                  @RequestParam @NotBlank(message = "email can't be empty") String email) throws MessagingException {
        ResponseObject<String> res = loginService.checkForgotPassword(email);
        return new ResponseEntity<>(res,res.getStatus());
    }
}
