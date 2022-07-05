package com.example.book.store.service;

import com.example.book.store.entities.User;
import com.example.book.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String checkLogin(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {

            User user = userOptional.get();
            String pw = user.getPassword();

            if (BCrypt.checkpw(password, pw))
                return "Login Success";

            else return "Wrong Password";
        }
        return "Username not exist";
    }
}
