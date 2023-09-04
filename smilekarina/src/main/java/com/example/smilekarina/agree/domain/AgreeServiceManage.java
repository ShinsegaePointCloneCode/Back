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
public class AgreeServiceManage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "agree_check")
    private Boolean agreeCheck;
    @Column(nullable = false, name = "user_id")
    private Long userId;
    @Column(nullable = false)
    @Convert(converter = AgreeTypeConverter.class)
    private AgreeType agreeType;
    public void setAgreeCheck(Boolean agreeCheck) {
        this.agreeCheck =agreeCheck;
    }
}
