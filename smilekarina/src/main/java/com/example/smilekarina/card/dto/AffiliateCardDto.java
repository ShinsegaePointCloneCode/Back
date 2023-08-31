package com.example.smilekarina.card.dto;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateCardDto {

    private Long userId;
    private String cardNumber;
    private String issueStore;
    private String lastName;

}
