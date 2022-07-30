package com.example.book.store.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-manager")
public class UserController {
    /**
     * chỉ admin có quyền thêm, xóa, sửa username
     * user có quyền xem thông tin của user
     * admin có quyền xem thông tin của user trừ mật khẩu.
     *
     */


    // tạo user
    @PostMapping("/create")
}
