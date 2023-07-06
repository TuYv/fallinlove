package com.max.fallinlove.finance.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.base.enums.AmountTypeEnum;
import com.max.fallinlove.base.exception.BusinessException;
import com.max.fallinlove.base.utils.DateUtil;
import com.max.fallinlove.finance.dto.AggregateBillingDTO;
import com.max.fallinlove.finance.dto.DailyDetailDTO;
import com.max.fallinlove.finance.dto.MonthTagAmountDTO;
import com.max.fallinlove.finance.repository.MonthAmountDetailRepository;
import com.max.fallinlove.finance.repository.MonthAmountRepository;
import com.max.fallinlove.finance.req.FinanceReq;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import com.max.fallinlove.finance.mapper.MonthAmountDetailMapper;
import com.max.fallinlove.finance.mapper.MonthAmountMapper;
import com.max.fallinlove.finance.service.IMonthAmountDetailService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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

    @Override
    public List<MonthAmountDetail> getUserMonthAmountDetail(int accountId) {
        QueryWrapper<MonthAmount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        List<MonthAmount> monthList = monthAmountMapper.selectList(queryWrapper);
        List<Integer> monthIds = Optional.of(monthList).orElse(new ArrayList<>()).stream().map(MonthAmount::getId).collect(Collectors.toList());
        QueryWrapper<MonthAmountDetail> detailQueryWrapper = new QueryWrapper<>();
        detailQueryWrapper.in("month_amount_id", monthIds);
        return monthAmountDetailMapper.selectList(detailQueryWrapper);
    }

    @Override
    public AggregateBillingDTO getAggregateInfo(Integer accountId) {
        List<MonthAmountDetail> detailList = this.getUserMonthAmountDetail(accountId);
        AggregateBillingDTO dto = AggregateBillingDTO.build(accountId);
        if (CollectionUtils.isNotEmpty(detailList)) {
            dto.setTotalCount(detailList.size());
            List<String> dayList = detailList.stream().map(detail -> "" + detail.getTime().getYear() + detail.getTime().getDayOfYear()).distinct().collect(Collectors.toList());
            dto.setTotalDays(dayList.size());
        }
        return dto;
    }

    @Override
    public DailyDetailDTO getDailyDetail(Date date, Integer accountId) {
        DailyDetailDTO dto = new DailyDetailDTO();
        QueryWrapper<MonthAmount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", accountId);
        List<MonthAmount> monthList = monthAmountMapper.selectList(queryWrapper);
        List<Integer> monthIds = Optional.of(monthList).orElse(new ArrayList<>()).stream().map(MonthAmount::getId).collect(Collectors.toList());
        QueryWrapper<MonthAmountDetail> detailQueryWrapper = new QueryWrapper<>();
        detailQueryWrapper.in("month_amount_id", monthIds);
        detailQueryWrapper.between("time", DateUtil.getDayStartTime(date), DateUtil.getDayEndTime(date));
        List<MonthAmountDetail> detailList = monthAmountDetailMapper.selectList(detailQueryWrapper);
        dto.setDetailList(detailList);
        Map<String, List<MonthAmountDetail>> typedDetailMap = detailList.stream().collect(Collectors.groupingBy(MonthAmountDetail::getAmountType));
        Optional.ofNullable(typedDetailMap.get(AmountTypeEnum.INCOME.getType()))
            .flatMap(incomeDetail -> incomeDetail.stream().map(MonthAmountDetail::getAmount).reduce(BigDecimal::add)).ifPresent(dto::setIncome);
        Optional.ofNullable(typedDetailMap.get(AmountTypeEnum.SPEND.getType()))
            .flatMap(spendDetail -> spendDetail.stream().map(MonthAmountDetail::getAmount).reduce(BigDecimal::add)).ifPresent(dto::setSpend);

        return dto;
    }
}
