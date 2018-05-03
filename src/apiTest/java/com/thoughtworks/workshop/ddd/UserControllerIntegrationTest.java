package com.thoughtworks.workshop.ddd;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreatedWhenPostAnValidUser() throws Exception {
        performRequest(post("/users")
                .content("{\"email\":\"123@163.com\",\"policyNumber\":\"123\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturn400WhenUserEmailAlreadyExist() throws Exception {
        performRequest(post("/users")
                .content("{\"email\":\"user@163.com\",\"policyNumber\":\"123\"}"));

        performRequest(post("/users")
                .content("{\"email\":\"user@163.com\",\"policyNumber\":\"456\"}"))
                .andExpect(status().isBadRequest());
    }
}
