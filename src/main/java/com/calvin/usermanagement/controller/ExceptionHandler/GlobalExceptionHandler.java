package com.calvin.usermanagement.controller.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.calvin.usermanagement.exception.BizException;
import com.calvin.usermanagement.exception.ExceptionEnum;
import com.calvin.usermanagement.model.response.Response;
import com.calvin.usermanagement.model.response.ResponseCodes;
import netscape.javascript.JSObject;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

/**
 * @author Calvin Ye
 * @since 2019年08月01日 15:27
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public Response handleBizException(BizException e, HttpServletRequest request) {
        ExceptionEnum ee = ExceptionEnum.getByEClass(e.getClass());
        if (ee != null) {
            return Response.failure(e.getResponseCodes());
        }
        return e.getResponseCodes() == null ? new Response(-1, e.getMsg()) : Response.failure(e.getResponseCodes());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        JSONObject paramInvalidItems = new JSONObject();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            paramInvalidItems.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return Response.failure(ResponseCodes.USER_PARAM_IS_INVALID, paramInvalidItems);
    }
}
