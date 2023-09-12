package com.example.smilekarina.receipt.application;

import com.example.smilekarina.point.vo.PointContentOut;
import com.example.smilekarina.receipt.vo.SmartReceiptPointIn;

public interface ReceiptService {

    // 스마트 영수증 조회가 가능한 포인트 등록
    void registerSmartReceipt(String token, SmartReceiptPointIn smartReceiptPointIn);

    // 스마트 영수증 조회가 가능한 곳 적립/사용 포인트 조회
    PointContentOut getSmartReceipt(Long pointId);

    // 일반 적립/사용 포인트 조회
    PointContentOut getReceiptAccept(Long pointId);

}
