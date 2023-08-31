package com.example.smilekarina.card.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlinePointCardDto {

    private String cardNumber;
    private String issueStore;
    private LocalDateTime createdDate;

}
