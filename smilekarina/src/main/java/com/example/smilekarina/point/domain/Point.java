package com.example.smilekarina.point.domain;

import com.example.smilekarina.global.domain.BaseEntity;
import com.example.smilekarina.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 포인트
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Point extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "point", columnDefinition = "int default 0")
    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false, name = "used", columnDefinition = "boolean default false")
    private Boolean used;

    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
    @Convert(converter = PointTypeConverter.class)
    private PointType pointType;

    @Column(nullable = false, name = "total_point", columnDefinition = "int default 0")
    private Integer totalPoint;

}
