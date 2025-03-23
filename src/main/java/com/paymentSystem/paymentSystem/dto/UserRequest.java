package com.paymentSystem.paymentSystem.dto;

import com.paymentSystem.paymentSystem.entity.User;

public record UserRequest (
        String name,
        String email,
        String password
){
    public User toModel(){
        return new User(name, email, password);
    }
}
