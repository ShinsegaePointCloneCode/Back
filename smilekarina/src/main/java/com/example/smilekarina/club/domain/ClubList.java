package com.example.smilekarina.club.domain;

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
public class ClubList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @Column(nullable = false, name = "beauty", columnDefinition = "boolean default false")
    private Boolean beauty;
    @Column(nullable = false, name = "mom_kids", columnDefinition = "boolean default false")
    private Boolean momKids;
    @Column(nullable = false, name = "car", columnDefinition = "boolean default false")
    private Boolean car;
    @Column(nullable = false,  name = "biz", columnDefinition = "boolean default false")
    private Boolean biz;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false)
    private User user;
}
