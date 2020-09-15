package com.calvin.usermanagement.controller;

import java.lang.reflect.MalformedParametersException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.calvin.usermanagement.exception.BizException;
import com.calvin.usermanagement.model.request.RegisterOrLoginRequest;
import com.calvin.usermanagement.model.response.Response;
import com.calvin.usermanagement.model.request.UpdatePwdRequest;
import com.calvin.usermanagement.model.request.UpdateUserInfoRequest;
import com.calvin.usermanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
// @RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static AtomicLong count = new AtomicLong(0);

    @RequestMapping("/")
    public String index(String request) {
//        log.info("receive request count {}", count.incrementAndGet());
//        return "success";
        throw new HttpMessageNotReadableException("test", new MalformedParametersException("test"));
    }

    @RequestMapping("/resetCount")
    public String resetCount(String request) {
//        count.set(0);
//        log.info("receive request count {}", count.incrementAndGet());
//        return "success";
        throw new HttpMessageNotReadableException("test");
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response register(@Validated @RequestBody RegisterOrLoginRequest request) {
        return userService.register(request);
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response login(@Validated @RequestBody RegisterOrLoginRequest request) {
        return userService.login(request);
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
            @Validated @RequestBody UpdateUserInfoRequest requestBody) {
        return userService.updateUserInfo(requestHeader, requestBody);
    }

    @PostMapping(path = "/updatePwd", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response updatePwd(@RequestHeader("X-Authorization") String[] requestHeader,
            @Validated @RequestBody UpdatePwdRequest requestBody) {
        return userService.updatePwd(requestHeader, requestBody);
    }

}
