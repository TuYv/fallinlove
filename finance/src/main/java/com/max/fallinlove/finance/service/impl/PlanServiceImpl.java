package com.max.fallinlove.finance.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.entity.Plan;
import com.max.fallinlove.finance.mapper.PlanMapper;
import com.max.fallinlove.finance.req.PlanReq;
import com.max.fallinlove.finance.service.IPlanService;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 计划表 服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2022-02-07
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements IPlanService {

    @Override
    public void savePlan(PlanReq planReq) {
        Plan plan = new Plan();
        plan.setUserId(planReq.getUserId());
        plan.setPlanId(generatePlanId(planReq.getUserId()));
        plan.setPlanName(planReq.getPlanName());
        plan.setPurposes(planReq.getPurposes());

        this.save(plan);
    }

    private String generatePlanId(int userId) {
        return UUID.randomUUID().toString();
    }
}
