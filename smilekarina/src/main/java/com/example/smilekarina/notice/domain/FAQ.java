package com.example.smilekarina.notice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30, name ="big_option")
    private String bigOption;
    @Column(nullable = false, length=30, name="small_option")
    private String smallOption;
    @Column(nullable = false, length=100, name="faq_tittle")
    private String faqTitle;
    @Column(nullable = false, name = "faq_content",columnDefinition = "TEXT")
    private String faqContent;
    @Column(nullable = false, name = "faq_date",columnDefinition = "int default 0")
    private Integer faqDate;
    @Column(nullable = false, name = "answer",columnDefinition = "TEXT")
    private String answer;

}
