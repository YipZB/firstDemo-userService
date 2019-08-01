package com.calvin.usermanagement.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calvin.usermanagement.exception.BizException;
import com.calvin.usermanagement.model.response.ResponseCodes;
import com.calvin.usermanagement.repo.token.Token;
import com.calvin.usermanagement.repo.token.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Calvin Ye
 * @since 2019年07月31日 16:03
 */

@Slf4j
public class LoginedAuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.debug("in prehandle");
        if (handler instanceof HandlerMethod) {
            String[] xAuth = request.getHeader("X-Authorization").split(",");
            Token token = new Token(xAuth[0], xAuth[1]);
            ResponseCodes responseCodes = tokenManager.checkToken(token);
            if (!responseCodes.equals(ResponseCodes.SUCCESS)) {
                log.debug("false1");
                throw new BizException(responseCodes);
            }
            log.debug("success1");
            return true;
        }
        return true;
    }
}
