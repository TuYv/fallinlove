package com.max.fallinlove.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 月度标签金额DTO
 * @author Rick
 * @date 2022-02-09 15:57
 */
@Data
public class MonthTagAmountDTO {
    private String tagName;
    private BigDecimal amount;
}
