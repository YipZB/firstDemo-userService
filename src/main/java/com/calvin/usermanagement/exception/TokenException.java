package com.calvin.usermanagement.exception;


import com.calvin.usermanagement.model.response.ResponseCodes;

/**
 * @author Calvin Ye
 * @since 2019年08月01日 10:49
 */
public class TokenException extends BizException {
    public TokenException() {
        super();
    }

    public TokenException(Object data) {
        super();
        super.data = data;
    }

    public TokenException(String msg) {
        super(msg);
    }

    public TokenException(ResponseCodes responseCodes) {
        super(responseCodes);
    }

    public TokenException(ResponseCodes responseCodes, Object data) {
        super(responseCodes, data);
    }
}
