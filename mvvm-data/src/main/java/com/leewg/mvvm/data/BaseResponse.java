package com.leewg.mvvm.data;

/**
 * Created by leewg on 2017/5/10.
 * 该类仅供参考，实际业务返回的固定字段, 根据需求来定义，
 */
public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public boolean isOk() {
        return code == 0;
    }

    public String getMessage() {
        return msg;
    }
}
