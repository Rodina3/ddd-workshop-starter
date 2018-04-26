package com.thoughtworks.workshop.ddd.user.domain.service;


import com.thoughtworks.workshop.ddd.exception.EmailAlreadyRegisteredException;
import com.thoughtworks.workshop.ddd.exception.ResourceNotFoundException;
import com.thoughtworks.workshop.ddd.user.domain.model.User;
import com.thoughtworks.workshop.ddd.user.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegisterServiceTest {

    private RegisterService registerService;

    @Mock
    private UserRepo userRepo;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        registerService = new RegisterService(userRepo);
    }

    @Test
    public void shouldRegisterSucceed() throws Exception {
        given(userRepo.findUserByEmail("email")).willReturn(null);

        User user = registerService.createUser("email", "policyNumber");

        assertThat(user.getEmail(), is("email"));
        assertThat(user.getPolicyNumber(), is("policyNumber"));
    }

    @Test(expected = EmailAlreadyRegisteredException.class)
    public void shouldRegisterFail() throws Exception {
        given(userRepo.findUserByEmail("email")).willReturn(User.builder().build());

        registerService.createUser("email", "policyNumber");
    }

    @Test
    public void shouldInitPasswordSucceed() throws Exception {
        given(userRepo.findUserById(1L)).willReturn(User.builder().id(1L).build());

        User user = registerService.initPassword(1L, "password");

        assertThat(user.getId(), is(1L));
        assertThat(user.getPassword(), is("password"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldInitPasswordFail() throws Exception {
        given(userRepo.findUserById(1L)).willReturn(null);

        registerService.initPassword(1L, "password");
    }
}