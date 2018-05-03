package com.thoughtworks.workshop.ddd.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@ApiModel(description = "User init password model")
public class InitPasswordCommand {
    @NotNull
    @ApiModelProperty(value = "id", example = "067e6162-3b6f-4ae2-a171-2470b63dff00")
    private String id;

    @NotBlank(message = "Password cannot be empty")
    @ApiModelProperty(value = "password", example = "Buaa123./")
    private String password;

    @Tolerate
    public InitPasswordCommand() {
    }
}
