package com.thoughtworks.workshop.ddd.user.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private String id;
    private String email;
    private String policyNumber;
    private String password;

    public void initPassword(String password){
        this.password = password;
    }
}
