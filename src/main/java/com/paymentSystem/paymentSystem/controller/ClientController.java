package com.paymentSystem.paymentSystem.controller;

import com.paymentSystem.paymentSystem.dto.AuthenticationRequest;
import com.paymentSystem.paymentSystem.dto.AuthenticationResponse;
import com.paymentSystem.paymentSystem.dto.UserRequest;
import com.paymentSystem.paymentSystem.dto.UserResponse;
import com.paymentSystem.paymentSystem.entity.Client;
import com.paymentSystem.paymentSystem.service.TokenService;
import com.paymentSystem.paymentSystem.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService service;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/post")
    public ResponseEntity<UserResponse> userResgister(@RequestBody @Valid UserRequest userRequest){
        Client client = userRequest.toModel();
        return ResponseEntity.ok().body(service.userRegister(client));
    }

    @GetMapping("/get")
    public List<Client> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest login){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                login.email(), login.password()
        );

        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken( (Client) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        if(service.verify(code)){
            return "verify_success";
        }else{
            return "verify_fail";
        }
    }

}












