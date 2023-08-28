package com.example.smilekarina.club.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizOut {
    private String bizCompany;
    private Integer bizRegNumber;
    private String bizRepresentative;
    private String bizAddress;
    private String bizEmail;
    private Boolean personalInfo;
}
