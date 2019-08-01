package com.calvin.usermanagement.aop.interceptor;

import java.lang.annotation.*;

/**
 * @author Administrator
 * @since 2019年07月31日 23:43
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginedAuth {
}
