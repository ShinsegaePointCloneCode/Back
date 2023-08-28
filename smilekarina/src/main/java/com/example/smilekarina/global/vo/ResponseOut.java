package com.example.smilekarina.global.vo;


import lombok.*;


@Getter
@Setter
public class ResponseOut<T> {
    private boolean isSuccess;
    private int code;
    private T result;

    public static <T> ResponseOut<T> success() {
        ResponseOut<T> re = new ResponseOut<>();
        re.setSuccess(true);
        re.setCode(1000);
        re.setResult(null);
        return re;
    }
    public static <T> ResponseOut<T> fail() {
        ResponseOut<T> re = new ResponseOut<>();
        re.setSuccess(false);
        re.setCode(1000);
        re.setResult(null);
        return re;
    }
    public static <T> ResponseOut<T> success(T result) {
        ResponseOut<T> re = new ResponseOut<>();
        re.setSuccess(true);
        re.setCode(1000);
        re.setResult(result);
        return re;
    }
    public static <T> ResponseOut<T> fail(T result) {
        ResponseOut<T> re = new ResponseOut<>();
        re.setSuccess(false);
        re.setCode(1000);
        re.setResult(result);
        return re;
    }
    public static <T> ResponseOut<T> checkLogId(T result) {
        ResponseOut<T> re = new ResponseOut<>();
        re.setSuccess(false);
        re.setCode(3100);
        re.setResult(result);
        return re;
    }

}
