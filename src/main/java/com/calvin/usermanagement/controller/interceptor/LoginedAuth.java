package com.calvin.usermanagement.controller.interceptor;

import java.lang.annotation.*;

/**
 * @author Calvin Ye
 * @since 2019年07月31日 15:57
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginedAuth {

}
