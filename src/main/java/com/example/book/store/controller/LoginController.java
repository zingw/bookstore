package com.example.book.store.controller;

import com.example.book.store.dto.response.LoginResponse;
import com.example.book.store.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public LoginResponse login(@RequestParam String username,
                               @RequestParam String password) throws JsonProcessingException {
        return loginService.checkLogin(username,password);
    }
}
