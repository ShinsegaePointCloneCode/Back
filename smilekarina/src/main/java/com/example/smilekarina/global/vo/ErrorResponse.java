package com.example.smilekarina.global.vo;

import com.example.smilekarina.global.exception.ErrorStateCode;
import com.example.smilekarina.user.exception.UserErrorStateCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private Boolean isSuccess;
    private int code;
    private String message;

    public ErrorResponse(ErrorStateCode e) {
        this.isSuccess = e.isSuccess();
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponse(UserErrorStateCode e) {
        this.isSuccess = e.isSuccess();
        this.code = e.getCode();
        this.message = e.getMessage();
    }
}
