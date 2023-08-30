package com.example.smilekarina.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.coyote.ErrorState;

@Getter
@AllArgsConstructor
public class TokenInvalidException extends RuntimeException{
    private final ErrorStateCode errorStateCode;

}
