package com.max.fallinlove.finance.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.fallinlove.finance.dto.MonthPlanDTO;
import com.max.fallinlove.finance.entity.MonthPlan;
import com.max.fallinlove.finance.mapper.MonthPlanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rick
 * @date 2022-02-08 20:24
 */
@Repository
public class MonthPlanRepository {
    @Resource private MonthPlanMapper monthPlanMapper;

    public List<MonthPlanDTO> queryMonthPlanList(Integer accountId) {
        QueryWrapper<MonthPlan> qw = new QueryWrapper<>();
        qw.eq("account_id", accountId);

        final List<MonthPlan> monthPlans = monthPlanMapper.selectList(qw);
        final List<MonthPlanDTO> result = monthPlans.stream().map(monthPlan -> {
            MonthPlanDTO dto = new MonthPlanDTO();
            BeanUtils.copyProperties(monthPlan, dto);

            return dto;
        }).collect(Collectors.toList());

        return result;
    }
}
