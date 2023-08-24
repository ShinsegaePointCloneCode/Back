package com.example.smilekarina.receipt.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SmartReceiptPointIn {

    private Integer franchiseId;
    private String branchName;
    private String receiptNumber;
    private Integer point;
    private String pointType;

}
