package com.example.smilekarina.user.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SamePasswordException extends RuntimeException{
    private final UserErrorStateCode userErrorStateCode;
}
