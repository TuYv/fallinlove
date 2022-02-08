package com.max.fallinlove.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.finance.dto.PlanDTO;
import com.max.fallinlove.finance.entity.Plan;
import com.max.fallinlove.finance.req.JoinPlanReq;
import com.max.fallinlove.finance.req.PlanReq;

import java.util.List;

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

    /**
     * 根据用户Id获取所有计划
     * todo 一个人多个计划 多个人一个计划未作区分
     * @param userId
     * @return
     */
    List<PlanDTO> queryPlanList(Integer accountId);
}
