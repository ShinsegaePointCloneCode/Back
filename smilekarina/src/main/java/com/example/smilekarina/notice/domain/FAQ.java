package com.example.smilekarina.notice.domain;

import com.example.smilekarina.global.domain.BaseEntity;
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
//FAQ
public class FAQ extends BaseEntity {
    //extends BaseEntitiy를 작성하고 저장하면 자동으로 createdDate와 updatedDate가 생성됨
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
    @Column(nullable = false, name = "faq_answer",columnDefinition = "TEXT")
    private String faqAnswer;
}
