package com.example.smilekarina.agree.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreeAdvertiseIn {
    private Boolean optionOne;
    private Boolean optionTwo;
    private Boolean agreeEmail;
    private Boolean letter;
    private Boolean dm;
    private Boolean tm;
}
