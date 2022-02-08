package com.max.fallinlove.finance.req;

import lombok.Data;

/**
 * 加入计划req
 *
 * @author Rick
 * @date 2022-02-08 10:57
 */
@Data
public class JoinPlanReq {
    /**
     * 加入者Id
     */
    private int userId;
    /**
     * 加入的计划Id
     */
    private String planId;
}
