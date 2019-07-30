package com.calvin.usermanagement.repo.token;

/**
 * @author Calvin Ye
 * @since 2019年07月26日 14:45
 */
public class Token {
    private String userId;
    private String tokenInfo;
    private long expiresIn;

    public Token() {

    }

    public Token(String userId, String tokenInfo) {
        this.userId = userId;
        this.tokenInfo = tokenInfo;
    }

    public Token(String userId, String tokenInfo, long expiresIn) {
        this.userId = userId;
        this.tokenInfo = tokenInfo;
        this.expiresIn = expiresIn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
