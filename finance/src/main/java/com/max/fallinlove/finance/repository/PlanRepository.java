package com.max.fallinlove.finance.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.fallinlove.finance.entity.Plan;
import com.max.fallinlove.finance.mapper.PlanMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 计划仓储服务
 * @author Rick
 * @date 2022-02-08 11:08
 */
@Repository
public class PlanRepository {

    @Resource private PlanMapper planMapper;

    public Plan getByPlanId(String planId) {
        QueryWrapper<Plan> qw = new QueryWrapper<>();
        qw.eq("plan_id", planId);

        return planMapper.selectOne(qw);
    }
}
