package com.calvin.usermanagement.exception;

import com.calvin.usermanagement.model.response.ResponseCodes;

/**
 * @author Calvin Ye
 * @since 2019年08月01日 9:44
 */
public enum ExceptionEnum {
    /**
     * 参数无效
     */
//    PARAMETER_INVALID(),
    /**
     * 用户未登陆
     */
    UNAUTHORIZED(TokenException.class, ResponseCodes.TOKEN_EXPIRED_ERROR);

    private Class<? extends BizException> eClass;

    private ResponseCodes responseCodes;

    ExceptionEnum(Class<? extends BizException> eClass, ResponseCodes responseCodes) {
        this.eClass = eClass;
        this.responseCodes = responseCodes;
    }

    public Class<? extends BizException> geteClass() {
        return eClass;
    }

    public ResponseCodes getResponseCodes() {
        return responseCodes;
    }

    public static boolean isSupportException(Class<?> z) {
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (exceptionEnum.eClass.equals(z)) {
                return true;
            }
        }
        return false;
    }

    public static ExceptionEnum getByEClass(Class<? extends BizException> eClass) {
        if (eClass == null) {
            return null;
        }
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (eClass.equals(exceptionEnum.eClass)) {
                return exceptionEnum;
            }
        }
        return null;
    }


}
