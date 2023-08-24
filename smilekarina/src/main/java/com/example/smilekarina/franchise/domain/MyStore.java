package com.example.smilekarina.franchise.domain;

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
//자주찾는 매장
public class MyStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //즐겨찾기 매장 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;  //user id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Branch branch;  //Branch id
}
