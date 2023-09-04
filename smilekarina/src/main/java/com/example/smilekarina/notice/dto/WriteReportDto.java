package com.example.smilekarina.notice.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WriteReportDto {
    private String bigOption;   //대분류
    private String smallOption; //소분류
    private String reportTitle; //제목
    private String reportContent;   //내용
}
