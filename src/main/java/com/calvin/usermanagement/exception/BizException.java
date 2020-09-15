package com.calvin.usermanagement.exception;

import com.calvin.usermanagement.model.response.ResponseCodes;

/**
 * @author Calvin Ye
 * @since 2019年08月01日 9:32
 */
public class BizException extends RuntimeException {
    protected ResponseCodes responseCodes;
    protected int code;
    protected String msg;
    protected Object data;

    public BizException() {
        ExceptionEnum exceptionEnum = ExceptionEnum.getByEClass(this.getClass());
        if (exceptionEnum != null) {
            this.responseCodes = exceptionEnum.getResponseCodes();
            this.code = exceptionEnum.getResponseCodes().code();
            this.msg = exceptionEnum.getResponseCodes().msg();
        }
    }

    public BizException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BizException(String msg) {
        this();
        this.msg = msg;
    }

    public BizException(ResponseCodes responseCodes, Object data) {
        this(responseCodes);
        this.data = data;
    }

    public BizException(ResponseCodes responseCodes) {
        this.responseCodes = responseCodes;
        this.code = responseCodes.code();
        this.msg = responseCodes.msg();
    }

    public ResponseCodes getResponseCodes() {
        return responseCodes;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
