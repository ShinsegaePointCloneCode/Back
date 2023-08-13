package com.example.smilekarina.global.vo;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOut<T> {
    private boolean isSuccess;
    private int code;
    private T result;

    public static <T> ResponseOut<T> success(T result) {
        ResponseOut<T> re = new ResponseOut<>();
        re.setSuccess(true);
        re.setCode(1000);
        re.setResult(result);
        return re;
    }

    public static <T> ResponseOut<T> checkLog(T result) {
        ResponseOut<T> re = new ResponseOut<>();
        re.setSuccess(false);
        re.setCode(3100);
        re.setResult(result);
        return re;
    }

}
