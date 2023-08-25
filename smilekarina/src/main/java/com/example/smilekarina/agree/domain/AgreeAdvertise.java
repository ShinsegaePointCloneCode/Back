package com.example.smilekarina.agree.domain;

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
public class AgreeAdvertise{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 1, name = "option_one", columnDefinition = "int default 0")
    private Boolean optionOne;
    @Column(nullable = false, length = 1, name = "option_two", columnDefinition = "int default 0")
    private Boolean optionTwo;
    @Column(nullable = false, length = 1, name = "agree_email", columnDefinition = "int default 0")
    private Boolean agreeEmail;
    @Column(nullable = false, length = 1, name = "letter", columnDefinition = "int default 0")
    private Boolean letter;
    @Column(nullable = false, length = 1, name = "DM", columnDefinition = "int default 0")
    private Boolean DM;
    @Column(nullable = false, length = 1, name = "TM", columnDefinition = "int default 0")
    private Boolean TM;
}
