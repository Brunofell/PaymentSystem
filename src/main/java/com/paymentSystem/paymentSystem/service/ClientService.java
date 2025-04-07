package com.paymentSystem.paymentSystem.service;

import com.paymentSystem.paymentSystem.dto.UserResponse;
import com.paymentSystem.paymentSystem.entity.Client;
import com.paymentSystem.paymentSystem.repository.ClientRepository;
import com.paymentSystem.paymentSystem.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;

    public List<Client> getAllUsers() {
        return repository.findAll();
    }


    public UserResponse userRegister(Client client){
        if(repository.findByEmail(client.getEmail()) != null){
            throw new RuntimeException("This email already exists ;-;");
        }
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);

        String randomCode = RandomString.generateRandonString(64);
        client.setVerificationCode(randomCode);
        client.setEnabled(true);

        Client savedClient = repository.save(client);
        UserResponse userResponse = new UserResponse(savedClient.getId(), savedClient.getName(), savedClient.getEmail(), savedClient.getPassword());
        return userResponse;

    }

    public boolean verify(String verificationCode){
        Client client = repository.findByVerificationCode(verificationCode);
        if(client == null | client.isEnabled()){
            return false;
        }else{
            client.setVerificationCode(null);
            client.setEnabled(true);
            repository.save(client);
            return true;
        }
    }

}
