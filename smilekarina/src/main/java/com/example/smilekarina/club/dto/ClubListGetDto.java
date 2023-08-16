package com.example.smilekarina.club.dto;

import com.example.smilekarina.user.domain.User;
import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClubListGetDto {
    private Boolean beauty;
    private Boolean momKids;
    private Boolean car;
    private Boolean biz;
    private User user;
}
