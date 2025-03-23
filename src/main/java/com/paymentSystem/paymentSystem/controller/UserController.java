package com.paymentSystem.paymentSystem.controller;

import com.paymentSystem.paymentSystem.dto.UserRequest;
import com.paymentSystem.paymentSystem.entity.User;
import com.paymentSystem.paymentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/post")
    public ResponseEntity<User> userResgister(@RequestBody UserRequest userRequest){
        User user = userRequest.toModel();
        return ResponseEntity.ok().body(service.userRegister(user));
    }

    // Get all users
    @GetMapping("/get")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

}
