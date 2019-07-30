package com.calvin.usermanagement.service;

import com.calvin.usermanagement.model.RegisterOrLoginRequest;
import com.calvin.usermanagement.model.Response;
import com.calvin.usermanagement.model.UpdatePwdRequest;
import com.calvin.usermanagement.model.UpdateUserInfoRequest;
import org.springframework.validation.BindingResult;

public interface UserService {
    Response register(RegisterOrLoginRequest request, BindingResult bindingResult);

    Response login(RegisterOrLoginRequest request, BindingResult bindingResult);

    Response logout(String[] request);

    Response getUserInfo(String[] request);

    Response updateUserInfo(String[] requestHeader, UpdateUserInfoRequest requestBody, BindingResult bindingResult);

    Response updatePwd(String[] requestHeader, UpdatePwdRequest requestBody, BindingResult bindingResult);

}
