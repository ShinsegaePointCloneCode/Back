package com.example.smilekarina.agree.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AgreeAdvertiseDto {
    private Boolean optionOne;
    private Boolean optionTwo;
    private Boolean agreeEmail;
    private Boolean letter;
    private Boolean DM;
    private Boolean TM;
}
