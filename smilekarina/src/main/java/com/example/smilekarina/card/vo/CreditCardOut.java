package com.example.smilekarina.card.vo;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardOut {

    private String cardName;
    private String cardNumber;
    private String issuePlace;
    private LocalDateTime createdDate;

}
