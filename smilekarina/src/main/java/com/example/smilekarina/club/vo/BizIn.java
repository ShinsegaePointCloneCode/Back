package com.example.smilekarina.club.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizIn {
    private String bizCompany;
    private Integer bizRegNumber;
    private String bizRepresentative;
    private String bizAddress;
    private String bizEmail;
    private Boolean personalInfo;
}
