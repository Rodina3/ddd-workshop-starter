package com.thoughtworks.workshop.ddd.user.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class RegisterCommand {
    @NotBlank
    private String email;
    @NotBlank
    private String policyNumber;
}
