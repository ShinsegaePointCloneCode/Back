package com.example.smilekarina.alarm.domain;

import com.example.smilekarina.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "push_content")
    private String pushContent;
    @Column(name = "is_read")
    private Boolean isRead;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(nullable = false, name = "letter_type")
    private Integer letterType;
    public void setRead(boolean read) {
        isRead = read;
    }
}
