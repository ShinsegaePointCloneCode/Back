package com.example.smilekarina.global.exception;

import com.example.smilekarina.user.exception.UserErrorStateCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SameDayCheckException extends RuntimeException{
    private final ErrorStateCode errorStateCode;
}
