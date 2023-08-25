package com.example.smilekarina.agree.domain;

import jakarta.persistence.*;

@Entity
public class Agree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long agreeAdvertise;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private AgreeServiceManage agreeService;



}
