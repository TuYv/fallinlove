package com.max.fallinlove.finance.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 预算req
 *
 * @author Rick
 * @date 2022-02-08 11:36
 */
@Data
public class MonthPlanReq {

    private int userId;

    private String planType;

    private BigDecimal planAmount;
}
