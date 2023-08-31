package com.example.smilekarina.card.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnlinePointCardOut {

    private String cardNumber;
    private String issueStore;
    private LocalDateTime createdDate;

}
