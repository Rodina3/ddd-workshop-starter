package com.thoughtworks.workshop.ddd.user.domain.service;


import com.thoughtworks.workshop.ddd.exception.EmailAlreadyRegisteredException;
import com.thoughtworks.workshop.ddd.exception.ResourceNotFoundException;
import com.thoughtworks.workshop.ddd.user.domain.model.User;
import com.thoughtworks.workshop.ddd.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterService {

    private UserRepo userRepo;

    @Autowired
    public RegisterService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(String email, String policyNumber) {
        checkEmail(email);
        checkPolicy(policyNumber);
        String id = UUID.randomUUID().toString();
        sendEmail(id);
        return User.builder()
                .id(id)
                .email(email)
                .policyNumber(policyNumber)
                .build();
    }

    public User initPassword(String id, String password) {
        Optional<User> userOpt = Optional.ofNullable(userRepo.findUserById(id));
        userOpt.orElseThrow(() -> new ResourceNotFoundException("User Not Found."));
        userOpt.get().initPassword(password);
        return userOpt.get();
    }

    private void checkEmail(String email) {
        Optional<User> userOpt = Optional.ofNullable(userRepo.findUserByEmail(email));
        if (userOpt.isPresent()) {
            throw new EmailAlreadyRegisteredException("Email Already Registered.");
        }
    }

    private void checkPolicy(String policyNumber) {

    }

    private void sendEmail(String id) {
        System.out.println(String.format("Sending Email to user: %s\n", id));
    }
}
