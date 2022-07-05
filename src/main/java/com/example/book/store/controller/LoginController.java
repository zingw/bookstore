package com.example.book.store.controller;

import com.example.book.store.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
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
    public String login(@RequestParam String username,
                        @RequestParam String password){
        return loginService.checkLogin(username,password);
    }
}
