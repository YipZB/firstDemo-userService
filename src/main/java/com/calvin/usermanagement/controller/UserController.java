package com.calvin.usermanagement.controller;

import com.calvin.usermanagement.model.RegisterOrLoginRequest;
import com.calvin.usermanagement.model.Response;
import com.calvin.usermanagement.model.UpdatePwdRequest;
import com.calvin.usermanagement.model.UpdateUserInfoRequest;
import com.calvin.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response register(@Validated  @RequestBody RegisterOrLoginRequest request, BindingResult bindingResult) {
        return userService.register(request, bindingResult);
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response login(@Validated @RequestBody RegisterOrLoginRequest request, BindingResult bindingResult) {
        return userService.login(request, bindingResult);
    }

    @PostMapping(path = "/logout", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response logout(@RequestHeader("X-Authorization") String[] request) {
        return userService.logout(request);
    }

    @PostMapping(path = "/getUserInfo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getUserInfo(@RequestHeader("X-Authorization") String[] request) {
        return userService.getUserInfo(request);
    }

    @PostMapping(path = "/updateUserInfo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response updateUserInfo(@RequestHeader("X-Authorization") String[] requestHeader,
            @Validated @RequestBody UpdateUserInfoRequest requestBody, BindingResult bindingResult) {
        return userService.updateUserInfo(requestHeader, requestBody, bindingResult);
    }

    @PostMapping(path = "/updatePwd", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response updatePwd(@RequestHeader("X-Authorization") String[] requestHeader,
            @Validated @RequestBody UpdatePwdRequest requestBody, BindingResult bindingResult) {
        return userService.updatePwd(requestHeader, requestBody, bindingResult);
    }

}
