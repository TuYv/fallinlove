package com.max.fallinlove.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.finance.entity.Plan;
import com.max.fallinlove.finance.req.JoinPlanReq;
import com.max.fallinlove.finance.req.PlanReq;

/**
 * <p>
 * 计划表 服务类
 * </p>
 *
 * @author max.tu
 * @since 2022-02-07
 */
public interface IPlanService extends IService<Plan> {

    void savePlan(PlanReq planReq);

    void joinPlan(JoinPlanReq joinPlanReq);
}
