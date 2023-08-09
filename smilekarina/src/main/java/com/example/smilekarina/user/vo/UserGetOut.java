package com.example.smilekarina.user.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGetOut {
    private String loginId;
    private String userName;
    private String email;
    private String phone;
    private String address;
}
