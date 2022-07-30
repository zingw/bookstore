package com.example.book.store.service;

import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.reqlogin.LoginReq;
import com.example.book.store.entities.User;
import com.example.book.store.dto.response.LoginResponse;
import com.example.book.store.jwt.JwtHelper;
import com.example.book.store.repository.PermissionRepository;
import com.example.book.store.repository.RolePermissionRepository;
import com.example.book.store.repository.UserRepository;
import com.example.book.store.repository.UserRoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public ResponseObject<LoginResponse> checkLogin(LoginReq req) throws JsonProcessingException {
        String username = req.getUsername();
        String password = req.getPassword();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && !userOptional.get().getIsDeleted()) {

            User user = userOptional.get();
            String pw = user.getPassword();

            if (BCrypt.checkpw(password, pw)){
                return ResponseObject.success(new LoginResponse(tokenGenerator(user)));
            }

            else return  ResponseObject.failed("PASSWORD_FAILED",HttpStatus.BAD_REQUEST);
        }
        return ResponseObject.failed("Username not exist",HttpStatus.BAD_REQUEST);
    }

    private String tokenGenerator(User user) {
        String userId = user.getId();
        String username = user.getUsername();
        List<String> authors = permissionRepository.getAuthors(userId);

        Map<String,Object> body = new HashMap<>();
        body.put("username",username);
        body.put("authors",authors);
        return JwtHelper.createToken(body);
    }


}
