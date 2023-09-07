package com.example.smilekarina.notice.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FAQOut {
    private String bigOption;
    private String smallOption;
    private String faqTitle;
    private String faqAnswer;
}
