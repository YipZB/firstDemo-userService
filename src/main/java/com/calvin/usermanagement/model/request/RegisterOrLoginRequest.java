package com.calvin.usermanagement.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegisterOrLoginRequest {

    @NotNull(message = "email should not be null")
    @Email(message = "-20101，email format error")
    private String email;

    @NotNull(message = "password should not be null")
    @Pattern(regexp = "[\\x21-\\x7e]{6,20}", message = "-20102，password format error")
    private String password;
}
