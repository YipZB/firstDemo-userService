package com.calvin.usermanagement.repo.token;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.calvin.usermanagement.model.ResponseCodes;
import com.calvin.usermanagement.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Calvin Ye
 * @since 2019年07月26日 15:02
 */

@Repository
public class TokenManagerImpl implements TokenManager {
    @Autowired
    private StringRedisTemplate redisTemplate;

    // 私有？

    @Override
    public String userIdKeyName(String userId) {
        return MessageFormat.format("calvin.user:user.id:{0}:token.list", userId);
    }

    @Override
    public String tokenKeyName(String tokenInfo) {
        return MessageFormat.format("token:{0}:user.id", tokenInfo);
    }

    @Override
    public Token addToken(String userId) {
        String tokenInfo = TokenUtil.createToken(userId);
        // 空指针，要使用try？
        // 同时写进去导致数量有7个？循环？try？
        if (redisTemplate.opsForList().size(userIdKeyName(userId)) >= 5) {
            deleteToken(new Token(userId, redisTemplate.opsForList().rightPop(userIdKeyName(userId))));
        }
        redisTemplate.opsForList().leftPush(userIdKeyName(userId), tokenInfo);
        // 时间常量
        long expiresIn = (long)24 * 3600 + new Random().nextInt(1000);
        redisTemplate.opsForValue().set(tokenKeyName(tokenInfo), userId, expiresIn, TimeUnit.SECONDS);

        return new Token(userId, tokenInfo, expiresIn);
    }

    @Override
    public ResponseCodes checkToken(Token token) {
        if (token == null) {
            return ResponseCodes.USER_PARAM_IS_INVALID;
        }
        String userIdFromRedis = redisTemplate.opsForValue().get(tokenKeyName(token.getTokenInfo()));
        if (userIdFromRedis == null) {
            return ResponseCodes.TOKEN_EXPIRED_ERROR;
        }
        if (!userIdFromRedis.equals(token.getUserId())) {
            return ResponseCodes.TOKEN_NOT_MATCH_ERROR;
        }
        return ResponseCodes.SUCCESS;
    }

    // 续期返回值？

    @Override
    public void renew(Token token) {
        redisTemplate.opsForList().remove(userIdKeyName(token.getUserId()), 1, token.getTokenInfo());
        redisTemplate.opsForList().leftPush(userIdKeyName(token.getUserId()), token.getTokenInfo());
        long expiresIn = (long)24 * 3600 + new Random().nextInt(1000);
        redisTemplate.expire(tokenKeyName(token.getTokenInfo()), expiresIn, TimeUnit.SECONDS);
    }

    @Override
    public void deleteToken(Token token) {
        redisTemplate.opsForList().remove(userIdKeyName(token.getUserId()), 1, token.getTokenInfo());
        redisTemplate.delete(tokenKeyName(token.getTokenInfo()));
    }

    @Override
    public void deleteTokenAll(String userId) {
        List<String> tokenInfoList = redisTemplate.opsForList().range(userIdKeyName(userId), 0, -1);
        List<String> tokenKeyNameList = new ArrayList<>();
        if (tokenInfoList != null) {
            for (String tokenInfo : tokenInfoList) {
                tokenKeyNameList.add(tokenKeyName(tokenInfo));
            }
            for (String tokenKeyName : tokenKeyNameList) {
                redisTemplate.delete(tokenKeyName);
            }
        }
        redisTemplate.delete(userIdKeyName(userId));
    }

}
