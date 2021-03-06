package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import com.max.fallinlove.finance.mapper.MonthAmountDetailMapper;
import com.max.fallinlove.finance.service.IMonthAmountDetailService;
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
public class MonthAmountDetailServiceImpl extends ServiceImpl<MonthAmountDetailMapper, MonthAmountDetail> implements IMonthAmountDetailService {

    @Autowired
    private MonthAmountDetailMapper monthAmountDetailMapper;

    @Override
    public List<MonthAmountDetail> getMonthAmountDetailList(int monthAmountId) {
        QueryWrapper<MonthAmountDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("month_amount_id",monthAmountId);
        return this.monthAmountDetailMapper.selectList(queryWrapper);
    }
}
