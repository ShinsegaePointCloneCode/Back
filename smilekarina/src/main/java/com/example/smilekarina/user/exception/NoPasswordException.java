package com.example.smilekarina.user.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoPasswordException  extends RuntimeException{
    private final UserErrorStateCode userErrorStateCode;
}
