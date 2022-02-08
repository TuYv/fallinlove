package com.max.fallinlove.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 计划DTO
 *
 * @author Rick
 * @date 2022-02-08 13:23
 */
@Data
public class PlanDTO {

    private Integer userId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 计划Id
     */
    private String planId;
    /**
     * 计划名称
     */
    private String planName;
    /**
     * 已存
     */
    private BigDecimal saved;
    /**
     * 目标
     */
    private BigDecimal purposes;

}
