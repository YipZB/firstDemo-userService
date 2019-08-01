package com.calvin.usermanagement;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.calvin.usermanagement.controller.interceptor.LoginedAuthInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.calvin.usermanagement.repo.user")
public class Application extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LoginedAuthInterceptor getLoginedAuthInterceptor() {
        return new LoginedAuthInterceptor();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //1.需要定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        //2.添加fashJson的配置消息，比如：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //3.在convert中添加配置信息
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //4.将convert添加到converters当中
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(getLoginedAuthInterceptor());
        // 排除配置
//        registration.excludePathPatterns("/error");
//        registration.excludePathPatterns("/login");
//        registration.excludePathPatterns("/register");
        // 拦截配置
        registration.addPathPatterns("/logout");
        registration.addPathPatterns("/getUserInfo");
        registration.addPathPatterns("/updateUserInfo");
        registration.addPathPatterns("/updatePwd");

    }
}
