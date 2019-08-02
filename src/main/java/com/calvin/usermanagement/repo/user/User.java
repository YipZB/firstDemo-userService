package com.calvin.usermanagement.repo.user;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {
    private Integer id;
    private String userId;
    private String email;
    private String password;
    private String createAt;
    private String updateAt;
    private String nickname;
    private String address;
}
