package com.example.book.store.service;

import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.reqlogin.LoginReq;
import com.example.book.store.dto.response.LoginResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.mail.MessagingException;

public interface LoginService {
    ResponseObject<LoginResponse> checkLogin(LoginReq req) throws JsonProcessingException;

    ResponseObject<String> checkForgotPassword(String email) throws MessagingException;
}
