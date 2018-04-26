package com.thoughtworks.workshop.ddd.user.controller;


import com.thoughtworks.workshop.ddd.user.UserApplicationService;
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
        userController.initPassword(1L, "password");
        verify(userApplicationService).initPassword(argThat(argument -> argument.getId().equals(1L)
                && argument.getPassword().equals("password")));
    }
}