package com.example.smilekarina.global.vo;

import com.example.smilekarina.global.exception.ErrorStateCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private Boolean isSuccess;
    private int code;
    private String message;

    public ErrorResponse(ErrorStateCode errorStateCode) {
        this.isSuccess = errorStateCode.isSuccess();
        this.code = errorStateCode.getCode();
        this.message = errorStateCode.getMessage();
    }
}
