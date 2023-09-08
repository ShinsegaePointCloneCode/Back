package com.example.smilekarina.card.dto;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateCardDto {

    private String token;
    private String cardNumber;
    private String issueStore;
    private String lastName;

}
