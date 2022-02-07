package com.max.fallinlove.finance.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 计划表
 * </p>
 *
 * @author max.tu
 * @since 2022-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    /**
     * 计划Id
     */
    private Integer planId;

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
