package com.example.smilekarina.agree.domain;

import com.example.smilekarina.user.domain.User;
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
public class AgreeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "agree_index")
    private Integer agreeIndex;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Agree agree;
}
