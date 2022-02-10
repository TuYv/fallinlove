package com.max.fallinlove.finance.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.base.exception.BusinessException;
import com.max.fallinlove.finance.dto.PlanDTO;
import com.max.fallinlove.finance.entity.Account;
import com.max.fallinlove.finance.entity.Plan;
import com.max.fallinlove.finance.mapper.PlanMapper;
import com.max.fallinlove.finance.repository.PlanRepository;
import com.max.fallinlove.finance.req.JoinPlanReq;
import com.max.fallinlove.finance.req.PlanReq;
import com.max.fallinlove.finance.service.IAccountService;
import com.max.fallinlove.finance.service.IPlanService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
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

    @Resource private IAccountService accountService;
    @Resource private PlanRepository planRepository;

    @Override
    public void savePlan(PlanReq planReq) {
        Plan plan = new Plan();
        plan.setAccountId(planReq.getAccountId());
        plan.setPlanId(generatePlanId(planReq.getAccountId()));
        plan.setPlanName(planReq.getPlanName());
        plan.setPurposes(planReq.getPurposes());

        this.save(plan);
    }

    @Override public void joinPlan(JoinPlanReq joinPlanReq) {
        Plan plan = planRepository.getByPlanId(joinPlanReq.getPlanId());
        if (plan == null) { throw new BusinessException("未查询到该计划"); }
        plan.setAccountId(joinPlanReq.getAccountId());
        plan.setSaved(BigDecimal.ZERO);

        this.save(plan);
    }

    @Override public List<PlanDTO> queryPlanList(Integer accountId) {
        List<Plan> list = planRepository.getByUserId(accountId);

        List<PlanDTO> result = list.stream().map(plan -> {
            PlanDTO dto = new PlanDTO();
            BeanUtils.copyProperties(plan, dto);
            dto.setNickName(accountService.getById(plan.getAccountId()).getNickName());

            return dto;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public void addAmount(int accountId, String planId, BigDecimal amount) {
        //1. 从账户中转出
        accountService.updateAmountById(accountId, amount.negate());
        //2. 转入计划中
        planRepository.addAmount(accountId, planId, amount);
    }

    //todo 抽出生成id的单独代码
    private String generatePlanId(int accountId) {
        return UUID.randomUUID().toString();
    }
}
