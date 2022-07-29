package com.example.book.store.service;

import com.example.book.store.entities.Permission;
import com.example.book.store.entities.RolePermission;
import com.example.book.store.entities.User;
import com.example.book.store.dto.response.LoginResponse;
import com.example.book.store.repository.PermissionRepository;
import com.example.book.store.repository.RolePermissionRepository;
import com.example.book.store.repository.UserRepository;
import com.example.book.store.repository.UserRoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public LoginResponse checkLogin(String username, String password) throws JsonProcessingException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {

            User user = userOptional.get();
            String pw = user.getPassword();

            if (BCrypt.checkpw(password, pw)){
                return new LoginResponse("Login Success", tokenGenerator(user), HttpStatus.OK);
            }

            else return  new LoginResponse("Login Failed",HttpStatus.NOT_FOUND);
        }
        return new LoginResponse("Username not exist",HttpStatus.NOT_FOUND);
    }

    private String tokenGenerator(User user) throws JsonProcessingException {
        String userId = user.getId();
        List<String> roleList = userRoleRepository.findRoleIdsByUserId(userId);
        List<String> permissionNameList = generatePermissionNameList(roleList);
        EncodeToken encodeToken = new EncodeToken(user.getUsername(),permissionNameList);
        String jsonTokenBeforeEncode = new ObjectMapper().writeValueAsString(encodeToken);

        return Base64.getEncoder().encodeToString(jsonTokenBeforeEncode.getBytes());

    }

    private List<String> generatePermissionNameList(List<String> rollIdList) {
        List<RolePermission> rolePermissionList = rolePermissionRepository.getRolePermissionList(rollIdList);

        Set<String> permissionSet = rolePermissionList
                .stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toSet());

        return rolePermissionRepository
                .getPermissionList(permissionSet)
                .stream().map(Permission::getName)
                .collect(Collectors.toList());
    }


}
