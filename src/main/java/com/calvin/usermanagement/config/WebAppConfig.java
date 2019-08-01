package com.calvin.usermanagement.config;

import com.calvin.usermanagement.aop.interceptor.LoginedAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @author Administrator
 * @since 2019年08月01日 0:08
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {
    @Autowired
    private LoginedAuthInterceptor loginedAuthInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginedAuthInterceptor).addPathPatterns("/");
    }
}
