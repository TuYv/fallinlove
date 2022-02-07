package com.max.fallinlove.finance.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.mapper.MonthAmountMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 月度消费仓储服务
 * @author Rick
 * @date 2022-02-07 16:49
 */
@Repository
public class MonthAmountRepository {
    @Resource
    private MonthAmountMapper monthAmountMapper;


    public MonthAmount getByTime(String year, String month) {
        QueryWrapper<MonthAmount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year",year);
        queryWrapper.eq("month",month);
        return monthAmountMapper.selectOne(queryWrapper);
    }
}
