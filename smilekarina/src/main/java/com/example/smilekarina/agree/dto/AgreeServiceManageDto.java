package com.example.smilekarina.agree.dto;

import com.example.smilekarina.agree.domain.AgreeType;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AgreeServiceManageDto {
    private AgreeType agreeType;
    private Boolean agreeCheck;
}
