package com.paymentSystem.paymentSystem.repository;

import com.paymentSystem.paymentSystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientRepository extends JpaRepository<Client, Long> {
    UserDetails findByEmail(String email);
    Client findByVerificationCode(String verificationCode );
}
