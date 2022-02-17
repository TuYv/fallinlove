package com.max.fallinlove.finance.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.dto.MonthPlanDTO;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.entity.MonthPlan;
import com.max.fallinlove.finance.mapper.MonthPlanMapper;
import com.max.fallinlove.finance.repository.MonthAmountDetailRepository;
import com.max.fallinlove.finance.repository.MonthAmountRepository;
import com.max.fallinlove.finance.repository.MonthPlanRepository;
import com.max.fallinlove.finance.req.MonthPlanReq;
import com.max.fallinlove.finance.service.IMonthPlanService;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Resource private MonthAmountRepository monthAmountRepository;
    @Resource private MonthAmountDetailRepository monthAmountDetailRepository;

    @Override public void saveMonthPlan(MonthPlanReq req) {
        MonthPlan monthPlan = new MonthPlan();
        monthPlan.setAccountId(req.getAccountId());
        monthPlan.setPlanType(req.getPlanType());
        monthPlan.setPlanAmount(req.getPlanAmount());

        this.save(monthPlan);
    }

    @Override public List<MonthPlanDTO> queryMonthPlan(Integer accountId) {

        List<MonthPlanDTO> result = monthPlanRepository.queryMonthPlanList(accountId);


        //1.获取当前月的总记录
        int month = LocalDate.now().getMonthValue();
        String m = month < 10 ? "0" + month : String.valueOf(month);
        String y = String.valueOf(LocalDate.now().getYear());
        MonthAmount monthAmount = monthAmountRepository.getByTime(accountId, y, m);

        List<MonthPlanDTO> newList = monthAmountDetailRepository.getUsedAmountForMonthPlan(monthAmount.getId());
        result.forEach(x -> {
            newList.forEach(z -> {
                if (x.getId().equals(z.getId())) {x.setUsedAmount(z.getUsedAmount());}
            });
            if (ObjectUtil.isNull(x.getUsedAmount())) {x.setUsedAmount(BigDecimal.ZERO);}
        });

        return result;
    }
}
