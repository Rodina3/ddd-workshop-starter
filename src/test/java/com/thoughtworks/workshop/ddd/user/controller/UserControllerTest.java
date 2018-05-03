package com.thoughtworks.workshop.ddd.user.controller;


import com.thoughtworks.workshop.ddd.user.UserApplicationService;
import com.thoughtworks.workshop.ddd.user.dto.InitPasswordCommand;
import com.thoughtworks.workshop.ddd.user.dto.RegisterCommand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserApplicationService userApplicationService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        userController = new UserController(userApplicationService);
    }

    @Test
    public void shouldRegister() throws Exception {
        RegisterCommand registerCommand = RegisterCommand.builder()
                .email("email")
                .policyNumber("policyNumber")
                .build();
        userController.registerUser(registerCommand);
        verify(userApplicationService).register(registerCommand);
    }

    @Test
    public void shouldInitPassword() throws Exception {
        InitPasswordCommand initPasswordCommand = InitPasswordCommand.builder()
                .id("067e6162-3b6f-4ae2-a171-2470b63dff00")
                .password("password")
                .build();
        userController.initPassword(initPasswordCommand);
        verify(userApplicationService).initPassword(argThat(argument ->
                argument.getId().equals("067e6162-3b6f-4ae2-a171-2470b63dff00")
                        && argument.getPassword().equals("password")));
    }
}