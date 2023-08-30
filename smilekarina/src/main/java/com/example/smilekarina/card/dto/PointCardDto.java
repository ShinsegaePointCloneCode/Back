package com.example.smilekarina.card.dto;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointCardDto {

    private Long userId;
    private String cardNumber;
    private String issueType;
    private String issueStore;

}
