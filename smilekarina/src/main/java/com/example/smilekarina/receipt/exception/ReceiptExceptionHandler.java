package com.example.smilekarina.receipt.exception;

import com.example.smilekarina.global.exception.ErrorStateCode;
import com.example.smilekarina.global.vo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ReceiptExceptionHandler {

    /*
        해당하는 영수증 정보가 없음
     */
    @ExceptionHandler(NoReceiptException.class)
    protected ResponseEntity<ErrorResponse> handlerReceiptExist(NoReceiptException ex) {
        log.error("noReceiptException", ex);
        ErrorResponse errorResponse = new ErrorResponse(ReceiptErrorStateCode.NORECEIPT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
