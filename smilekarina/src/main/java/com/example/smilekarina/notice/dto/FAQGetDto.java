package com.example.smilekarina.notice.dto;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FAQGetDto {
    private String bigOption;
    private String smallOption;
    private String faqTitle;
    private String faqAnswer;
}
