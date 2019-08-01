package com.calvin.usermanagement.aop.interceptor;

import com.calvin.usermanagement.model.ResponseCodes;
import com.calvin.usermanagement.repo.token.Token;
import com.calvin.usermanagement.repo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * @author Administrator
 * @since 2019年07月31日 23:48
 */

@Component
public class LoginedAuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final Class<?> clazz = ((HandlerMethod) handler).getBeanType();
            final Method method = ((HandlerMethod) handler).getMethod();
            if (clazz.isAnnotationPresent(LoginedAuth.class) || method.isAnnotationPresent(LoginedAuth.class)) {
                String xAuth = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();

//                String id = request.
//                if (!tokenManager.checkToken(new Token(xAuth[0], xAuth[1])).equals(ResponseCodes.SUCCESS)) {
//                    System.out.println("not success");
//                    return false;
//                }
                System.out.println(xAuth);
                if (xAuth == null) {
                    System.out.println("in interceptor:false");
                    return false;
                }
                System.out.println("in interceptor:true");
                return true;
            }
        }

        return super.preHandle(request, response, handler);
    }
}
