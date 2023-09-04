package com.example.smilekarina.agree.vo;

import com.example.smilekarina.agree.domain.AgreeType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgreeServiceManageIn {
    private AgreeType agreeType;
    private Boolean agreeCheck;
}
