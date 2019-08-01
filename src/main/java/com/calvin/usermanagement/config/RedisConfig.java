package com.calvin.usermanagement.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Configuration;

/**
 * @author Calvin Ye
 * @since 2019/7/26 12:06
 */

@Configuration
public class RedisConfig extends CachingConfigurerSupport {
}
