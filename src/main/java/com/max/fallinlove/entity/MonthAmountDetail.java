package com.max.fallinlove.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MonthAmountDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer monthAmountId;

    private LocalDateTime time;

    private String amountType;

    private BigDecimal amount;

    private String reason;

    private String remark;


}
