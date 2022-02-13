package com.max.fallinlove.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Rick
 * @date 2022-02-08 20:19
 */
@Data
public class MonthPlanDTO {
    private Integer id;
    private String planType;
    private BigDecimal planAmount;
}