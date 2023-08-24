package com.example.smilekarina.notice.dto;

import jakarta.persistence.Column;
import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportGetDto {
    private String bigOption;   //대분류
    private String smallOption; //소분류
    private String reportTitle; //제목
    private String reportContent;   //내용
    private String answerTitle; // 답변 제목
    private String answerContent;   //답변 내용
}
