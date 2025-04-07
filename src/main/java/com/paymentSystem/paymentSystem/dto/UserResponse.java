package com.paymentSystem.paymentSystem.dto;

public record UserResponse (
        Long id,
        String name,
        String email,
        String password
){
    public UserResponse toModel(){
        return new UserResponse(id, name, email, password);
    }
}
