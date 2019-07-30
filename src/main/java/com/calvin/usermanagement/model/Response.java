package com.calvin.usermanagement.model;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;

public class Response {
    @NotNull(message = "reponse code should not be null")
    private int code;
    private String msg;
    private JSONObject result;

    public Response() {

    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCodeAndMsg(ResponseCodes responseCodes) {
        this.code = responseCodes.code();
        this.msg = responseCodes.msg();
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public static Response success() {
        Response response = new Response();
        response.setCodeAndMsg(ResponseCodes.SUCCESS);
        return response;
    }

    public static Response success(JSONObject result) {
        Response response = new Response();
        response.setCodeAndMsg(ResponseCodes.SUCCESS);
        response.setResult(result);
        return response;
    }

    public static Response failure(ResponseCodes responseCodes) {
        Response response = new Response();
        response.setCodeAndMsg(responseCodes);
        return response;
    }

    public static Response failure(ResponseCodes responseCodes, JSONObject result) {
        Response response = new Response();
        response.setCodeAndMsg(responseCodes);
        response.setResult(result);
        return response;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public JSONObject getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "code:" + code + "\nmsg:" + msg + "\nresult:" + result.toJSONString();
    }
}
