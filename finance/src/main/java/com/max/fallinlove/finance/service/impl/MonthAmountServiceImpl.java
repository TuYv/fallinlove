package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.mapper.MonthAmountMapper;
import com.max.fallinlove.finance.service.IMonthAmountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
@Service
public class MonthAmountServiceImpl extends ServiceImpl<MonthAmountMapper, MonthAmount> implements IMonthAmountService {

    @Autowired
    private MonthAmountMapper monthAmountMapper;

    @Override
    public List<MonthAmount> getAllMonthAmountByAccountId(int accountId) {
        QueryWrapper<MonthAmount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        return monthAmountMapper.selectList(queryWrapper);
    }

    @Override
    public MonthAmount getByTime(String year, String month) {
        QueryWrapper<MonthAmount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year",year);
        queryWrapper.eq("month",month);
        return monthAmountMapper.selectOne(queryWrapper);
    }
}
