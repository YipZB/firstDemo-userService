package com.calvin.usermanagement.repo.token;

import java.util.List;

import com.calvin.usermanagement.model.ResponseCodes;

/**
 * @author Calvin Ye
 * @since 2019年07月26日 14:59
 */
public interface TokenManager {
    String userIdKeyName(String userId);

    String tokenKeyName(String tokenInfo);

    Token addToken(String userId);

    ResponseCodes checkToken(Token token);

    void renew(Token token);

    void deleteToken(Token token);

    void deleteTokenAll(String userId);
}
