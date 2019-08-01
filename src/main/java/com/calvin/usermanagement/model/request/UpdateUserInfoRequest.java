package com.calvin.usermanagement.model.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateUserInfoRequest {

    @Size(max = 32,message = "-20103，the size of nickname is out of 32")
    private String nickname;

    @Size(max = 255,message = "-20104，the size of address is out of 255")
    private String address;

}
