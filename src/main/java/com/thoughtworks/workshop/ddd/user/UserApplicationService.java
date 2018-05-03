package com.thoughtworks.workshop.ddd.user;


import com.thoughtworks.workshop.ddd.user.domain.model.User;
import com.thoughtworks.workshop.ddd.user.domain.service.RegisterService;
import com.thoughtworks.workshop.ddd.user.repo.UserRepo;
import com.thoughtworks.workshop.ddd.user.dto.RegisterCommand;
import com.thoughtworks.workshop.ddd.user.dto.InitPasswordCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {
    private RegisterService registerService;
    private UserRepo userRepo;

    @Autowired
    public UserApplicationService(RegisterService registerService,
                                  UserRepo userRepo) {
        this.registerService = registerService;
        this.userRepo = userRepo;
    }

    public String register(RegisterCommand registerCommand) {
        User user = registerService.createUser(registerCommand.getEmail(), registerCommand.getPolicyNumber());
        return userRepo.saveUser(user);
    }

    public void initPassword(InitPasswordCommand initPasswordCommand) {
        User user = registerService.initPassword(initPasswordCommand.getId(), initPasswordCommand.getPassword());
        userRepo.saveUser(user);
    }
}
