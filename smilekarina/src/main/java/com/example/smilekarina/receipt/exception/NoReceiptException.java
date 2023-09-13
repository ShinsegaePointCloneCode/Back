package com.example.smilekarina.receipt.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoReceiptException extends RuntimeException {
    private final ReceiptErrorStateCode receiptErrorStateCode;
}
