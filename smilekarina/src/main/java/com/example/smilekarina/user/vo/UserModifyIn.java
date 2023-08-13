package com.example.smilekarina.user.vo;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserModifyIn {
    private String address;
    private String email;
}
