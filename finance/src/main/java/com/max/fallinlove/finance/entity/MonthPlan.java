package com.max.fallinlove.finance.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 月度预算
 * </p>
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

    private Integer userId;

    /**
     * 预算名
     */
    private String planName;

    /**
     * 预算金额
     */
    private BigDecimal planAmount;


}
