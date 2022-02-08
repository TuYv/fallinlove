package com.max.fallinlove.finance.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 月度预算
 *
 * @author max.tu
 * @since 2022-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MonthPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer accountId;
    /**
     * 预算类型
     */
    private String planType;
    /**
     * 预算金额
     */
    private BigDecimal planAmount;
}
