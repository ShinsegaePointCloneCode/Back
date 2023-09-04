package com.example.smilekarina.agree.vo;

import com.example.smilekarina.agree.domain.AgreeType;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgreeServiceManageOut {
    private AgreeType agreeType;
    private Boolean agreeCheck;
    private LocalDateTime time;
}
