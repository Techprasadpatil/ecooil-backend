package com.ecooil.service;

import com.ecooil.dto.LoginResponse;
import com.ecooil.entity.User;
import com.ecooil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        userRepository.save(user);
        return "User Registered Successfully";
    }

    public LoginResponse loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        LoginResponse response = new LoginResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        // 🔥 FIXED
        response.setRole(user.getRole().toUpperCase());

        response.setMessage("Login Successful");

        return response;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}