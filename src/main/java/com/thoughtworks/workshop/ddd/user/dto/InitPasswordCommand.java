package com.thoughtworks.workshop.ddd.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InitPasswordCommand {
    private Long id;
    private String password;
}
