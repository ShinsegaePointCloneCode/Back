package com.example.smilekarina.receipt.presentation;

import com.example.smilekarina.global.vo.ResponseOut;
import com.example.smilekarina.receipt.application.ReceiptService;
import com.example.smilekarina.receipt.vo.SmartReceiptPointIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    /*
        영수증 인증 완료 후 적립
     */
    @PostMapping("/point/receiptpoint")
    public ResponseEntity<?> createSmartReceiptPoint(@RequestHeader("Authorization") String token, @RequestBody SmartReceiptPointIn smartReceiptPointIn) {

        receiptService.registerSmartReceipt(token, smartReceiptPointIn);
        ResponseOut<?> responseOut = ResponseOut.success();

        return ResponseEntity.ok(responseOut);
    }


}
