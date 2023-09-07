package com.example.smilekarina.gift.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GiftListOut {

    private Integer aTotalPoint;
    private Integer uTotalPoint;
    private List<GiftDetailListOut> giftDetailListOut;
    private Integer page;
    private Integer size;
    private Long totalRows;

}
