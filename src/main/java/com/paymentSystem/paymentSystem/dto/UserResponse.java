package com.paymentSystem.paymentSystem.dto;

import com.paymentSystem.paymentSystem.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
