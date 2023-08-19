package com.example.smilekarina.notice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//FAQ
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //faq id
    @Column(nullable = false, length = 30, name ="big_option")  //대분류
    private String bigOption;
    @Column(nullable = false, length=30, name="small_option")   //소분류
    private String smallOption;
    @Column(nullable = false, length=100, name="faq_tittle")    //faq 제목
    private String faqTitle;
    @Column(nullable = false, name = "faq_content",columnDefinition = "TEXT")   //faq 내용
    private String faqContent;
    /*
    @Column(nullable = false, name = "faq_date")
    private LocalDateTime faqDate;  //todo: faq 등록 날짜 -> extends baseEntity createdDate
    todo : LocalDateTime 말고 LocalDate도 고려해보기 -> api정의서보면서 고려해보기
    */
    @Column(nullable = false, name = "faq_answer",columnDefinition = "TEXT")
    private String faqAnswer;
}
