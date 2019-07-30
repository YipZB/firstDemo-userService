package com.calvin.usermanagement.model;

import javax.validation.constraints.Size;

public class UpdateUserInfoRequest {
    @Size(max = 32,message = "-20103，the size of nickname is out of 32")
    private String nickname;
    @Size(max = 255,message = "-20104，the size of address is out of 255")
    private String address;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
