package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.mapper.MonthAmountMapper;
import com.max.fallinlove.finance.repository.MonthAmountRepository;
import com.max.fallinlove.finance.service.IMonthAmountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    private MonthAmountMapper monthAmountMapper;
    @Resource
    private MonthAmountRepository monthAmountRepository;

    @Override
    public List<MonthAmount> getAllMonthAmountByAccountId(int accountId) {
        QueryWrapper<MonthAmount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        return monthAmountMapper.selectList(queryWrapper);
    }

    @Override
    public MonthAmount getByTime(Integer id, String year, String month) {
        //从数据库中查询
        MonthAmount monthAmount = monthAmountRepository.getByTime(year, month);
        if (Objects.isNull(monthAmount)) {
            //todo 可以设置成月初自动生成当月账单
            monthAmount = defaultMonthAmount(id, year, month);
        }
        return monthAmount;
    }

    /**
     * 设置默认月账单
     * @param id
     * @param year
     * @param month
     * @return
     */
    private MonthAmount defaultMonthAmount(Integer id, String year, String month) {
        MonthAmount monthAmount = new MonthAmount();
        monthAmount.setMonth(year);
        monthAmount.setAccountId(id);
        monthAmount.setYear(month);
        monthAmount.setSpend(BigDecimal.ZERO);
        monthAmount.setIncome(BigDecimal.ZERO);

        return monthAmount;
    }

}
