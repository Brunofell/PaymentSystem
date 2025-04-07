package com.paymentSystem.paymentSystem.dto;

import com.paymentSystem.paymentSystem.entity.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest (
        @NotNull(message = "Name cannot be null.")
        @NotBlank(message = "Name cannot be blank.")
        String name,
        @NotNull(message = "Email cannot be null.")
        @NotBlank(message = "Email cannot be blank.")
        String email,
        @NotNull(message = "Password cannot be null.")
        @NotBlank(message = "Password cannot be blank.")
        String password
){
    public Client toModel(){
        return new Client(name, email, password);
    }
}
