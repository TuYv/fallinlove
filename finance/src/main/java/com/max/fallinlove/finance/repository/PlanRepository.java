package com.max.fallinlove.finance.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.fallinlove.base.exception.BusinessException;
import com.max.fallinlove.finance.entity.Plan;
import com.max.fallinlove.finance.mapper.PlanMapper;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 根据userid获取所有关联的计划列表
     * @param accountId
     * @return
     */
    public List<Plan> getByUserId(Integer accountId) {
        //1. 查询这个用户关联的计划
        QueryWrapper<Plan> qw = new QueryWrapper<>();
        qw.eq("account_id", accountId);
        List<Plan> list = planMapper.selectList(qw);

        if (CollectionUtil.isEmpty(list) || list.size() == 0) { throw new BusinessException("该账户无关联计划");}

        //2. 根据查询出的计划获取所有关联的数据
        List<String> planIdList = list.stream().map(Plan::getPlanId).collect(Collectors.toList());
        qw = new QueryWrapper<>();
        qw.in("plan_id", planIdList);

        return planMapper.selectList(qw);
    }

    public void addAmount(int accountId, String planId, BigDecimal amount) {
        planMapper.updateSaved(accountId, planId, amount);
    }
}
