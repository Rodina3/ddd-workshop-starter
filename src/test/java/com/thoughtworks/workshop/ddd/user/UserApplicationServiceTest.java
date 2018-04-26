package com.thoughtworks.workshop.ddd.user;

import com.thoughtworks.workshop.ddd.user.domain.model.User;
import com.thoughtworks.workshop.ddd.user.domain.service.RegisterService;
import com.thoughtworks.workshop.ddd.user.dto.InitPasswordCommand;
import com.thoughtworks.workshop.ddd.user.dto.RegisterCommand;
import com.thoughtworks.workshop.ddd.user.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


public class UserApplicationServiceTest {

    private UserApplicationService userApplicationService;

    @Mock
    private RegisterService registerService;
    @Mock
    private UserRepo userRepo;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        userApplicationService = new UserApplicationService(registerService, userRepo);
    }

    @Test
    public void shouldRegisterUser() throws Exception {
        // given
        RegisterCommand registerCommand = RegisterCommand.builder()
                .email("email")
                .policyNumber("policyNumber")
                .build();
        User user = User.builder()
                .email("email")
                .policyNumber("policyNumber")
                .build();
        given(registerService.createUser(registerCommand.getEmail(), registerCommand.getPolicyNumber())).willReturn(user);
        given(userRepo.saveUser(user)).willReturn(1L);

        // when
        Long id = userApplicationService.register(registerCommand);

        //then
        assertThat(id, is(1L));
        verify(registerService).createUser("email", "policyNumber");
        verify(userRepo).saveUser(argThat(argument -> argument.getEmail().equals("email")
                && argument.getPolicyNumber().equals("policyNumber")));
    }

    @Test
    public void shouldInitPassword() throws Exception {
        // given
        InitPasswordCommand initPasswordCommand = InitPasswordCommand.builder()
                .id(1L)
                .password("password")
                .build();

        User user = User.builder()
                .email("email")
                .password("password")
                .build();
        given(registerService.initPassword(initPasswordCommand.getId(), initPasswordCommand.getPassword())).willReturn(user);

        // when
        userApplicationService.initPassword(initPasswordCommand);

        // then
        verify(registerService).initPassword(1L, "password");
        verify(userRepo).saveUser(argThat(argument -> argument.getEmail().equals("email")
                && argument.getPassword().equals("password")));
    }
}