package com.example.smilekarina.user.exception;

import com.example.smilekarina.global.exception.ErrorStateCode;
import com.example.smilekarina.global.exception.TokenInvalidException;
import com.example.smilekarina.global.vo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {
    /**
     같은 비밀번호
     */
    @ExceptionHandler(SamePasswordException.class)
    protected ResponseEntity<ErrorResponse> handleSameDay(SamePasswordException ex) {
        log.error("SamePasswordException", ex);
        ErrorResponse errorResponse = new ErrorResponse(UserErrorStateCode.SAMEPASSWORD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
    /**
     이전 비밀번호
     */
    @ExceptionHandler(SamePrePasswordException.class)
    protected ResponseEntity<ErrorResponse> handlePreSameDay(SamePrePasswordException ex) {
        log.error("SamePrePasswordException", ex);
        ErrorResponse errorResponse = new ErrorResponse(UserErrorStateCode.SAMEPREPASSWORD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
    /**
     동일 비밀번호
     */
    @ExceptionHandler(NoPasswordException.class)
    protected ResponseEntity<ErrorResponse> handlePreSameDay(NoPasswordException ex) {
        log.error("NoPasswordException", ex);
    ErrorResponse errorResponse = new ErrorResponse(UserErrorStateCode.NOPASSWORD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

}
