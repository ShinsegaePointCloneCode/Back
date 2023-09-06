package com.example.smilekarina.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorStateCode {
    SAMEPASSWORD(false, 3010,"현재 비밀번호와 동일합니다."),
    SAMEPREPASSWORD(false,3011,"이전 비밀번호와 동일합니다."),
    NOPASSWORD(false,3012,"비밀번호가 일치하지 않습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
