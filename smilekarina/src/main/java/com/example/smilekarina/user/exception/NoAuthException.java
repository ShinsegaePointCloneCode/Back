package com.example.smilekarina.user.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoAuthException extends RuntimeException{
    private final UserErrorStateCode userErrorStateCode;
}
