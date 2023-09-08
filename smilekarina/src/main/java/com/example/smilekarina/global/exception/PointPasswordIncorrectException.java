package com.example.smilekarina.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PointPasswordIncorrectException extends RuntimeException {

    private final ErrorStateCode errorStateCode;

}
