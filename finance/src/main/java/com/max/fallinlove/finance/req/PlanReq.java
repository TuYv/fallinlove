package com.max.fallinlove.finance.req;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author Max.Tu
 * @program common
 * @description 计划Req
 * @create 2022-02-07 23:19
 **/
@Data
public class PlanReq {
    private int userId;

    private String planName;

    private BigDecimal purposes;
}
