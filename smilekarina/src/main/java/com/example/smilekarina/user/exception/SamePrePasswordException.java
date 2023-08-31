package com.example.smilekarina.user.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SamePrePasswordException extends RuntimeException{
    private final UserErrorStateCode userErrorStateCode;
}
