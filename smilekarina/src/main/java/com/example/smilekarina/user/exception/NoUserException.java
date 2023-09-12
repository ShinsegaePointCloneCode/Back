package com.example.smilekarina.user.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoUserException  extends RuntimeException{
    private final UserErrorStateCode userErrorStateCode;
}
