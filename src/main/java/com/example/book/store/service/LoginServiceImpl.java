package com.example.book.store.service;

import com.example.book.store.entities.User;
import com.example.book.store.model.login.LoginResponse;
import com.example.book.store.model.token.EncodeToken;
import com.example.book.store.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Override
    public LoginResponse checkLogin(String username, String password) throws JsonProcessingException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {

            User user = userOptional.get();
            String pw = user.getPassword();

            if (BCrypt.checkpw(password, pw))
                return new LoginResponse("Login Success",tokenGenerator(user));

            else return new LoginResponse("Wrong password");
        }
        return new LoginResponse("Username not exist");
    }

    private String tokenGenerator(User user) throws JsonProcessingException {
        String userName = user.getUsername();
        List<String> authorList = List.of(user.getAuthor().split(","));
        EncodeToken encodeToken = new EncodeToken(userName,authorList);
        String s = new ObjectMapper().writeValueAsString(encodeToken);
        return Base64.getEncoder().encodeToString(s.getBytes());
    }


}
