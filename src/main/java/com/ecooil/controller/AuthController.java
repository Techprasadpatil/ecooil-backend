package com.ecooil.controller;

import com.ecooil.dto.LoginResponse;
import com.ecooil.entity.User;
import com.ecooil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // 🔥 FIXED LOGIN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        return userService.loginUser(user.getEmail(), user.getPassword());
    }

    // GET USER
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}