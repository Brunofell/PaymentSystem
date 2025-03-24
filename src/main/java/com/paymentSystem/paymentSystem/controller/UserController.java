package com.paymentSystem.paymentSystem.controller;

import com.paymentSystem.paymentSystem.dto.AuthenticationRequest;
import com.paymentSystem.paymentSystem.dto.AuthenticationResponse;
import com.paymentSystem.paymentSystem.dto.UserRequest;
import com.paymentSystem.paymentSystem.dto.UserResponse;
import com.paymentSystem.paymentSystem.entity.User;
import com.paymentSystem.paymentSystem.service.TokenService;
import com.paymentSystem.paymentSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/post")
    public ResponseEntity<UserResponse> userResgister(@RequestBody @Valid UserRequest userRequest){
        User user = userRequest.toModel();
        return ResponseEntity.ok().body(service.userRegister(user));
    }

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest login){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                login.email(), login.password()
        );

        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken( (User) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}
