package com.max.fallinlove.finance.cqe;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @program: eureka-demo
 * @description:
 * @author: Max.Tu
 * @create: 2019-09-04 20:57
 **/
@Data
public class FinanceQuery {

    private int id;

    private BigDecimal totalAmount;

    private String month;

    private String year;

    private int monthAmountDetailId;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private String amountType;

    private BigDecimal amount;

    private String reason;

    private String tagName;
    /**
     * 预算种类Id
     */
    private int monthPlanId;
}
