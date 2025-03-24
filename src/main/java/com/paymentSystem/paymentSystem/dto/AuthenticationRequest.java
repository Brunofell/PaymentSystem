package com.paymentSystem.paymentSystem.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
