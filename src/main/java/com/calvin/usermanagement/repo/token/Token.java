package com.calvin.usermanagement.repo.token;

import lombok.Data;

/**
 * @author Calvin Ye
 * @since 2019年07月26日 14:45
 */

@Data
public class Token {

    private String userId;
    private String tokenInfo;
    private long expiresIn;

    public Token(String userId, String tokenInfo) {
        this.userId = userId;
        this.tokenInfo = tokenInfo;
    }

    public Token(String userId, String tokenInfo, long expiresIn) {
        this.userId = userId;
        this.tokenInfo = tokenInfo;
        this.expiresIn = expiresIn;
    }
}
