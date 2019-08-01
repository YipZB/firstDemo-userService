package com.calvin.usermanagement.service;

import com.calvin.usermanagement.model.request.RegisterOrLoginRequest;
import com.calvin.usermanagement.model.response.Response;
import com.calvin.usermanagement.model.request.UpdatePwdRequest;
import com.calvin.usermanagement.model.request.UpdateUserInfoRequest;
import org.springframework.validation.BindingResult;

public interface UserService {
    Response register(RegisterOrLoginRequest request);

    Response login(RegisterOrLoginRequest request);

    Response logout(String[] request);

    Response getUserInfo(String[] request);

    Response updateUserInfo(String[] requestHeader, UpdateUserInfoRequest requestBody);

    Response updatePwd(String[] requestHeader, UpdatePwdRequest requestBody);

}
