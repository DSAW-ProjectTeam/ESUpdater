package com.midsummra.esupdater.common.result;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Result<T> {

    // 响应体
    private T data;

    // 响应信息
    private String msg;

    // 响应码
    private int code;

    private ObjectMapper objectMapper;

    private Result(){}

    private Result(T data, String msg, int code){
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static <T> Result<T> suc(){
        return suc(null);
    }

    public static <T> Result<T> suc(T data){
        return new Result<>(data, "succeed", 1);
    }

    public static <T> Result<T> err(String msg, int code){
        return new Result<>(null, msg, code);
    }

    public static <T> Result<T> err(String msg){
        return err(msg, -1);
    }


    @Override
    public String toString() {
        String val = null;

        try {
            val = objectMapper.writeValueAsString(this);
        }catch (Exception e){
            e.printStackTrace();
        }

        return val;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
