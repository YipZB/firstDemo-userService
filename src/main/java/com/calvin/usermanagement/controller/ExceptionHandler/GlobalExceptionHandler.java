package com.calvin.usermanagement.controller.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import com.calvin.usermanagement.exception.BizException;
import com.calvin.usermanagement.exception.ExceptionEnum;
import com.calvin.usermanagement.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Calvin Ye
 * @since 2019年08月01日 15:27
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public Response handleBizException(BizException e, HttpServletRequest request) {
        ExceptionEnum ee = ExceptionEnum.getByEClass(e.getClass());
        if (ee != null) {
            return Response.failure(e.getResponseCodes());
        }
        return e.getResponseCodes() == null ? new Response(-1, e.getMsg()) : Response.failure(e.getResponseCodes());
    }
}
