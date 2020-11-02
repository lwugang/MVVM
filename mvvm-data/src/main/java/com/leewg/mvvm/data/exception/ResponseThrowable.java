package com.leewg.mvvm.data.exception;

/**
 * Created by leewg on 2017/5/11.
 */

public class ResponseThrowable extends Exception {
    public int code;
    public String message;

    public ResponseThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ResponseThrowable(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
