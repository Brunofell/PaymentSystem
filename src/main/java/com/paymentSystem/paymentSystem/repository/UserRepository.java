package com.paymentSystem.paymentSystem.repository;

import com.paymentSystem.paymentSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
