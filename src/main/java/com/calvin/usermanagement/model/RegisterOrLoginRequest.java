package com.calvin.usermanagement.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegisterOrLoginRequest {

    @NotNull(message = "email should not be null")
    @Email(message = "-20101，email format error")
    private String email;
    @NotNull(message = "password should not be null")
    @Pattern(regexp = "[\\x21-\\x7e]{6,20}", message = "-20102，password format error")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
