package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.dto.MonthPlanDTO;
import com.max.fallinlove.finance.entity.MonthPlan;
import com.max.fallinlove.finance.mapper.MonthPlanMapper;
import com.max.fallinlove.finance.repository.MonthPlanRepository;
import com.max.fallinlove.finance.req.MonthPlanReq;
import com.max.fallinlove.finance.service.IMonthPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 月度预算 服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2022-02-07
 */
@Service
public class MonthPlanServiceImpl extends ServiceImpl<MonthPlanMapper, MonthPlan> implements IMonthPlanService {

    @Resource private MonthPlanRepository monthPlanRepository;

    @Override public void saveMonthPlan(MonthPlanReq req) {
        MonthPlan monthPlan = new MonthPlan();
        monthPlan.setAccountId(req.getAccountId());
        monthPlan.setPlanType(req.getPlanType());
        monthPlan.setPlanAmount(req.getPlanAmount());

        this.save(monthPlan);
    }

    @Override public List<MonthPlanDTO> queryMonthPlan(Integer accountId) {

        return monthPlanRepository.queryMonthPlanList(accountId);
    }
}
