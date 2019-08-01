package com.calvin.usermanagement.service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.calvin.usermanagement.model.request.RegisterOrLoginRequest;
import com.calvin.usermanagement.model.request.UpdatePwdRequest;
import com.calvin.usermanagement.model.request.UpdateUserInfoRequest;
import com.calvin.usermanagement.model.response.Response;
import com.calvin.usermanagement.model.response.ResponseCodes;
import com.calvin.usermanagement.repo.token.Token;
import com.calvin.usermanagement.repo.token.TokenManager;
import com.calvin.usermanagement.repo.user.User;
import com.calvin.usermanagement.repo.user.UserMapper;
import com.calvin.usermanagement.util.Pbkdf2Sha256;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

// 名称？

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private TokenManager tokenManager;

    @Override
    public Response register(RegisterOrLoginRequest request) {
        String registerEmail = request.getEmail();
        String registerPwd = request.getPassword();
        if (userMapper.selectByEmail(registerEmail) != null) {
            return Response.failure(ResponseCodes.USER_EXIST_ERROR);
        }
        String userId = UUID.randomUUID().toString();
        String encryptedPwd = Pbkdf2Sha256.encode(registerPwd, 1000);
        // SimpleDateFormat线程不安全
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String creatAt = df.format(new Date());
        // try 是否插入成功过,mysql错误码
        // 传一个User对象
        userMapper.insert(userId, registerEmail, encryptedPwd, creatAt, creatAt);

        JSONObject result = new JSONObject();
        result.put("userId", userId);
        result.put("createAt", creatAt);
        return Response.success(result);
    }

    @Override
    public Response login(RegisterOrLoginRequest request) {
        String loginEmail = request.getEmail();
        String loginPwd = request.getPassword();
        User user = userMapper.selectByEmail(loginEmail);
        if (user == null) {
            return Response.failure(ResponseCodes.USER_NOT_EXIST_ERROR);
        }
        if (!Pbkdf2Sha256.verification(loginPwd, user.getPassword())) {
            return Response.failure(ResponseCodes.USER_PWD_INCORRECT_ERROR);
        }
        Token loginToken = tokenManager.addToken(user.getUserId());

        JSONObject result = new JSONObject();
        result.put("token", loginToken.getTokenInfo());
        result.put("expiresIn", MessageFormat.format("{0}秒(s)", loginToken.getExpiresIn()));
        result.put("userId", user.getUserId());
        result.put("email", loginEmail);
        result.put("nickname", user.getNickname());
        result.put("address", user.getAddress());
        result.put("createAt", user.getCreateAt());
        result.put("updateAt", user.getUpdateAt());
        return Response.success(result);
    }

    @Override
    public Response logout(String[] request) {
        // 异常处理？？字段缺失或空
        tokenManager.deleteToken(new Token(request[0], request[1]));
        return Response.success();
    }

    @Override
    public Response getUserInfo(String[] request) {
        // 异常处理？字段缺失？
        tokenManager.renew(new Token(request[0], request[1]));
        User user = userMapper.selectByUserId(request[0]);
        JSONObject result = new JSONObject();
        result.put("userId", user.getUserId());
        result.put("email", user.getEmail());
        result.put("createAt", user.getCreateAt());
        result.put("updateAt", user.getUpdateAt());
        result.put("nickname", user.getNickname());
        result.put("address", user.getAddress());
        return Response.success(result);

    }

    @Override
    public Response updateUserInfo(String[] requestHeader, UpdateUserInfoRequest requestBody) {
        // 异常处理？
        // 支持emoji
        // 写的异常？
        tokenManager.renew(new Token(requestHeader[0], requestHeader[1]));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String updateAt = df.format(new Date());
        if (requestBody.getAddress() != null) {
            userMapper.updateAddressByUserId(requestHeader[0], requestBody.getAddress(), updateAt);
        }
        if (requestBody.getNickname() != null) {
            userMapper.updateNicknameByUserId(requestHeader[0], requestBody.getNickname(), updateAt);
        }
        return Response.success();

    }

    @Override
    public Response updatePwd(String[] requestHeader, UpdatePwdRequest requestBody) {
        tokenManager.renew(new Token(requestHeader[0], requestHeader[1]));
        if (!Pbkdf2Sha256.verification(requestBody.getOldPwd(), userMapper.selectPwdByUserId(requestHeader[0]))) {
            return Response.failure(ResponseCodes.USER_PWD_INCORRECT_ERROR);
        }
        String encryptedPwd = Pbkdf2Sha256.encode(requestBody.getNewPwd(), 1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String updateAt = df.format(new Date());
        userMapper.updatePwdByUserId(requestHeader[0], encryptedPwd, updateAt);
        tokenManager.deleteTokenAll(requestHeader[0]);
        return Response.success();

    }
}
