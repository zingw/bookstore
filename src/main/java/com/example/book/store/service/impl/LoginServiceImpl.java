package com.example.book.store.service.impl;

import com.example.book.store.constants.MailConstant;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.mail.DataMailDTO;
import com.example.book.store.dto.request.reqlogin.LoginReq;
import com.example.book.store.entities.User;
import com.example.book.store.dto.response.LoginResponse;
import com.example.book.store.jwt.JwtHelper;
import com.example.book.store.repository.PermissionRepository;
import com.example.book.store.repository.RolePermissionRepository;
import com.example.book.store.repository.UserRepository;
import com.example.book.store.repository.UserRoleRepository;
import com.example.book.store.service.LoginService;
import com.example.book.store.service.MailService;
import com.example.book.store.utils.KeyGen;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
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

    @Autowired
    MailService mailService;

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

    @Override
    public ResponseObject<String> checkForgotPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty())
            return ResponseObject.failed("EMAIL_NOT_FOUND",HttpStatus.BAD_REQUEST);
        User user = userOptional.get();
        try{
            user.setResetKey(KeyGen.gen6digitKey());
            userRepository.save(user);

            Map<String,Object> props = new HashMap<>();
            props.put("name",user.getName());
            props.put("username",user.getUsername());
            props.put("resetKey",user.getResetKey());

            DataMailDTO dataMailDTO = new DataMailDTO();
            dataMailDTO.setTo(email);
            dataMailDTO.setSubject(MailConstant.SUBJECT);
            dataMailDTO.setProps(props);

            mailService.sendHtmlMail(dataMailDTO,"mail");
            new Thread(() ->{
                try {
                    Thread.sleep(MailConstant.EXPIRE_TIME);
                    resetPasscode(user);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            return ResponseObject.success("PASSCODE DELIVERED");
        }
        catch (MessagingException e){
            return ResponseObject.failed("MESSAGE FAILED",HttpStatus.BAD_REQUEST);
        }

    }

    private void resetPasscode(User user) {
        user.setResetKey(null);
        userRepository.save(user);
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
