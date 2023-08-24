package com.example.smilekarina.receipt.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmartReceiptPointDto {

    private Integer franchiseId;
    private String branchName;
    private String receiptNumber;
    private Integer point;
    private String pointType;

}
