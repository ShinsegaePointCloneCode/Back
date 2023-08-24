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
//가맹점 통합 가입 리스트
public class CombJoined {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //가맹점 통합 가입 리스트 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;  //user id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Franchise franchise; //Franchise id
}
