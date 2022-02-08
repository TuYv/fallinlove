package com.max.fallinlove.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.finance.entity.MonthPlan;
import com.max.fallinlove.finance.req.MonthPlanReq;

/**
 * <p>
 * 月度预算 服务类
 * </p>
 *
 * @author max.tu
 * @since 2022-02-07
 */
public interface IMonthPlanService extends IService<MonthPlan> {

    void saveMonthPlan(MonthPlanReq req);
}
