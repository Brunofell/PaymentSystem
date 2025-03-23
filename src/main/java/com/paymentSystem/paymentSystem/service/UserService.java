package com.paymentSystem.paymentSystem.service;

import com.paymentSystem.paymentSystem.entity.User;
import com.paymentSystem.paymentSystem.repository.UserRepository;
import com.paymentSystem.paymentSystem.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return repository.findAll();
    }


    public User userRegister(User user){
        if(repository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists ;-;");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String randomCode = RandomString.generateRandonString(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        User savedUser = repository.save(user);
        return savedUser;

    }
}
