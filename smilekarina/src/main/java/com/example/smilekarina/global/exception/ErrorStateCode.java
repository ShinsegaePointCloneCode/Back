package com.example.smilekarina.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStateCode {
    UNAUTHORIXED(false,401, "권한이 없습니다"),
    ACCESS_TOKEN_EXPIRE(false,403, "ACCESS 토큰 만료"),
    REDIS(false,408, "캐시 서버에 문제가 있습니다"),
    RUNTIME(false,500, "서버 에러"),
    TOKEN_INVALID(false,400, "토큰 형식이 잘못되었습니다"),
    TOKEN_UNAUTH(false,402, "토큰이 일치하지 않습니다"),
//    GATEWAY(false,503, "게이트 웨이 요청에 문제가 있습니다"),
    SUCCESS(true,200, "성공");

    private final boolean isSuccess;
    private final int code;
    private final String message;

}
