package com.example.smilekarina.club.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BizGetDto {
    private String bizCompany;
    private Integer bizRegNumber;
    private String bizRepresentation;
    private String bizAddress;
    private String bizEmail;
    private Boolean personalInfo;
}
