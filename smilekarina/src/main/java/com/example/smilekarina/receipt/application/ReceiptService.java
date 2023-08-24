package com.example.smilekarina.receipt.application;

import com.example.smilekarina.receipt.vo.SmartReceiptPointIn;

public interface ReceiptService {

    // 스마트 영수증 조회가 가능한 포인트 등록
    void registerSmartReceipt(String token, SmartReceiptPointIn smartReceiptPointIn);

}
