package com.thoughtworks.workshop.ddd.user.controller;

import com.thoughtworks.workshop.ddd.user.UserApplicationService;
import com.thoughtworks.workshop.ddd.user.dto.InitPasswordCommand;
import com.thoughtworks.workshop.ddd.user.dto.RegisterCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(tags = "User", description = "User Related API")
public class UserController {
    private UserApplicationService userApplicationService;

    @Autowired
    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "User Register", notes = "Register")
    @PostMapping()
    public String registerUser(@Valid @RequestBody RegisterCommand registerCommand) {
        return userApplicationService.register(registerCommand);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Initial Password", notes = "Initial Password")
    @PutMapping()
    public void initPassword(@Valid @RequestBody InitPasswordCommand initPasswordCommand) {
        userApplicationService.initPassword(initPasswordCommand);
    }
}
