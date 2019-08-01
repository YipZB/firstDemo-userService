package com.calvin.usermanagement.model;

public enum ResponseCodes {
    SUCCESS(0, "请求成功"),

    USER_PARAM_IS_INVALID(-20100, "参数无效"),
    USER_EMAIL_FORMAT_ERROR(-20101, "email格式不合法"),
    USER_PWD_FORMAT_ERROR(-20102, "password格式不合法"),
    USER_NICKNAME_FORMAT_ERROR(-20103,"nickname格式不合法"),
    UESR_ADDRESS_FORMAT_ERROR(-20104,"address格式不合法"),
    USER_OLD_PWD_FORMAT_ERROR(-20105,"old password格式不合法"),
    USER_NEW_PWD_FORMAT_ERROR(-20106,"new password格式不合法"),
    MYSQL_STORAGE_ERROR(-20107, "MySQL存储错误"),

    USER_PWD_INCORRECT_ERROR(-20112, "密码不正确"),
    USER_NOT_EXIST_ERROR(-20113, "邮箱不存在"),

    TOKEN_EXPIRED_ERROR(-20201,"token无效（不存在，过期或登出）"),
    TOKEN_NOT_MATCH_ERROR(-20202,"token不属于该用户"),
    TOKEN_INFO_NOT_FOUND_ERROR(-20203,"token信息未获取"),

    USER_EXIST_ERROR(-20301, "邮箱已注册");

    private int code;
    private String msg;

    ResponseCodes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public static String getMsg(String name) {
        for (ResponseCodes responseCodes : ResponseCodes.values()) {
            if (responseCodes.name().equals(name)) {
                return responseCodes.msg;
            }
        }
        return name;
    }

    public static int getCode(String name) {
        for (ResponseCodes responseCodes : ResponseCodes.values()) {
            if (responseCodes.name().equals(name)) {
                return responseCodes.code;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
