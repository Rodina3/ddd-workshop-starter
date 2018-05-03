package com.thoughtworks.workshop.ddd.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ApiModel(description = "User register request model")
public class RegisterCommand {
    @NotBlank(message = "Email cannot be empty")
    @ApiModelProperty(value = "Email", example = "email@163.com")
    private String email;

    @NotBlank(message = "Policy Number cannot be empty")
    @ApiModelProperty(value = "Policy Number", example = "P123")
    private String policyNumber;

    @Tolerate
    public RegisterCommand() {
    }
}
