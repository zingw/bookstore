package com.example.book.store.service;

import com.example.book.store.model.login.LoginResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LoginService {
    LoginResponse checkLogin(String username, String password) throws JsonProcessingException;
}
