package com.thoughtworks.workshop.ddd.user.controller;

import com.thoughtworks.workshop.ddd.user.UserApplicationService;
import com.thoughtworks.workshop.ddd.user.dto.InitPasswordCommand;
import com.thoughtworks.workshop.ddd.user.dto.RegisterCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@Api(value = "User REST API", description = "User related API")
public class UserController {
    private UserApplicationService userApplicationService;

    @Autowired
    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "POST", notes = "Register")
    @PostMapping()
    public void registerUser(@Valid @RequestBody RegisterCommand registerCommand) {
        userApplicationService.register(registerCommand);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "POST", notes = "Init Password")
    @PostMapping("/{id}")
    public void initPassword(@NotNull @RequestParam Long id, @NotBlank @RequestBody String password) {
        InitPasswordCommand initPasswordCommand = InitPasswordCommand.builder()
                .id(id)
                .password(password)
                .build();
        userApplicationService.initPassword(initPasswordCommand);
    }
}
