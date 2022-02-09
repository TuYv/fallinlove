package com.max.fallinlove.finance.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.base.exception.BusinessException;
import com.max.fallinlove.finance.dto.MonthTagAmountDTO;
import com.max.fallinlove.finance.repository.MonthAmountDetailRepository;
import com.max.fallinlove.finance.repository.MonthAmountRepository;
import com.max.fallinlove.finance.req.FinanceReq;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import com.max.fallinlove.finance.mapper.MonthAmountDetailMapper;
import com.max.fallinlove.finance.mapper.MonthAmountMapper;
import com.max.fallinlove.finance.service.IMonthAmountDetailService;

import java.time.LocalDate;
import java.util.List;
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

    @Resource private MonthAmountMapper monthAmountMapper;
    @Resource private MonthAmountDetailMapper monthAmountDetailMapper;
    @Resource private MonthAmountRepository monthAmountRepository;
    @Resource private MonthAmountDetailRepository monthAmountDetailRepository;

    @Override public void saveMonthAmountDetail(int id, FinanceReq insertFinance) {
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
    public List<MonthTagAmountDTO> getMonthTagAmountList(Integer accountId) {
        //1.获取当前月的总记录
        int month = LocalDate.now().getMonthValue();
        String m = month < 10 ? "0" + month : String.valueOf(month);
        String y = String.valueOf(LocalDate.now().getYear());
        MonthAmount monthAmount = monthAmountRepository.getByTime(accountId, y, m);

        if (ObjectUtil.isNull(monthAmount)) {throw new BusinessException("该账户暂无记账记录");}

        //2.获取tag记录
        List<MonthTagAmountDTO> list = monthAmountDetailRepository.getMonthTagAmountList("0", monthAmount.getId());
        return list;
    }
}
