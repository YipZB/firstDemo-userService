package com.calvin.usermanagement.util;

import java.util.Date;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

/**
 * @author Calvin Ye
 * @since 2019年07月26日 14:13
 */
public class TokenUtil {
    private static final String SEPARATOR = "-";

    /**
     * Token格式：时间戳-userId-随机字符串
     */
    public static String createToken(String userId) {
        return System.currentTimeMillis() + SEPARATOR + userId;
    }

    /**
     * 解析Token，从中取得userId
     */
//    public static String getUserIdFromToken(String token) {
//        if (StringUtils.isEmpty(token)) {
//            return null;
//        }
//        String[] param = token.split(SEPARATOR);
//        if (param.length != 3) {
//            return null;
//        }
//        try {
//            return NumberUtils.createLong(param[1]);
//        } catch (NumberFormatException e) {
//            return null;
//        }
//    }
}
