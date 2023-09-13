package com.example.smilekarina.receipt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReceiptErrorStateCode {

    NORECEIPT(false,5110,"해당하는 영수증 정보가 없습니다.");

    private final boolean success;
    private final int code;
    private final String message;
}
