package com.max.fallinlove.finance.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.base.exception.BusinessException;
import com.max.fallinlove.finance.entity.Plan;
import com.max.fallinlove.finance.mapper.PlanMapper;
import com.max.fallinlove.finance.repository.PlanRepository;
import com.max.fallinlove.finance.req.JoinPlanReq;
import com.max.fallinlove.finance.req.PlanReq;
import com.max.fallinlove.finance.service.IPlanService;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 计划表 服务实现类
 *
 * @author max.tu
 * @since 2022-02-07
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements IPlanService {

    @Resource private PlanRepository planRepository;

    @Override
    public void savePlan(PlanReq planReq) {
        Plan plan = new Plan();
        plan.setUserId(planReq.getUserId());
        plan.setPlanId(generatePlanId(planReq.getUserId()));
        plan.setPlanName(planReq.getPlanName());
        plan.setPurposes(planReq.getPurposes());

        this.save(plan);
    }

    @Override public void joinPlan(JoinPlanReq joinPlanReq) {
        Plan plan = planRepository.getByPlanId(joinPlanReq.getPlanId());
        if (plan == null) { throw new BusinessException("未查询到该计划"); }
        plan.setUserId(joinPlanReq.getUserId());
        plan.setSaved(BigDecimal.ZERO);

        this.save(plan);
    }

    //todo 抽出生成id的单独代码
    private String generatePlanId(int userId) {
        return UUID.randomUUID().toString();
    }
}
