package com.ecooil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.ecooil.entity.User;
import com.ecooil.entity.OilRequest;
import com.ecooil.repository.UserRepository;
import com.ecooil.repository.OilRequestRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OilRequestRepository oilRequestRepository;

    @GetMapping("/owners")
    public List<Map<String, Object>> getOwners() {

        List<User> owners = userRepository.findAll();

        List<Map<String, Object>> result = new ArrayList<>();

        for (User user : owners) {

            if (!"OWNER".equals(user.getRole())) continue;

            Map<String, Object> map = new HashMap<>();

            map.put("name", user.getName());

            // 🔥 FIXED METHOD NAME
            OilRequest lastOil = oilRequestRepository
                    .findTopByUserIdOrderByRequestDateDesc(user.getId());

            map.put("lastOilDate", lastOil != null ? lastOil.getRequestDate() : "-");

            result.add(map);
        }

        return result;
    }
}