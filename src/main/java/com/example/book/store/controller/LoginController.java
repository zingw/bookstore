package com.example.book.store.controller;

import com.example.book.store.dto.request.LoginReq;
import com.example.book.store.dto.response.LoginResponse;
import com.example.book.store.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/home")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid
                               @RequestBody LoginReq loginReq) throws JsonProcessingException {
        String username = loginReq.getUsername();
        String password = loginReq.getPassword();
        LoginResponse loginResponse = loginService.checkLogin(username,password);
        return new ResponseEntity<>(loginResponse,loginResponse.getStatus());
    }
}
