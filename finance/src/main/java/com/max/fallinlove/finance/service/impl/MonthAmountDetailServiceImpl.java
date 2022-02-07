package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.cqe.FinanceQuery;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import com.max.fallinlove.finance.mapper.MonthAmountDetailMapper;
import com.max.fallinlove.finance.mapper.MonthAmountMapper;
import com.max.fallinlove.finance.service.IMonthAmountDetailService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MonthAmountDetailServiceImpl extends ServiceImpl<MonthAmountDetailMapper, MonthAmountDetail> implements IMonthAmountDetailService {

    @Resource
    private MonthAmountMapper monthAmountMapper;
    @Resource
    private MonthAmountDetailMapper monthAmountDetailMapper;

    @Override public void saveMonthAmountDetail(int id, FinanceQuery insertFinance) {
        MonthAmountDetail monthAmountDetail = new MonthAmountDetail();
        monthAmountDetail.setMonthAmountId(id);
        monthAmountDetail.setAmount(insertFinance.getAmount());
        monthAmountDetail.setAmountType(insertFinance.getAmountType());
        monthAmountDetail.setTime(insertFinance.getTime());
        monthAmountDetail.setReason(insertFinance.getReason());
        monthAmountDetail.setTagName(insertFinance.getTagName());
        monthAmountDetail.setMonthPlanId(insertFinance.getMonthPlanId());

        this.save(monthAmountDetail);
    }

    @Override
    public List<MonthAmountDetail> getMonthAmountDetailList(int monthAmountId) {
        QueryWrapper<MonthAmountDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("month_amount_id",monthAmountId);
        return this.monthAmountDetailMapper.selectList(queryWrapper);
    }

    @Override
    public List<MonthAmountDetail> getMonthTagAmountList() {
        //获取当前月的总记录
        int month = LocalDate.now().getMonthValue();
        String m = month < 10 ? "0" + month : String.valueOf(month);
        String y = String.valueOf(LocalDate.now().getYear());
        QueryWrapper<MonthAmount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year", y);
        queryWrapper.eq("month",m);
        MonthAmount monthAmount = monthAmountMapper.selectOne(queryWrapper);

        //获取tag记录
        List<MonthAmountDetail> list = monthAmountDetailMapper.queryMonthTagAmount("0", monthAmount.getId());
        return list;
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().getYear());

    }
}
