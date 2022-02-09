package com.max.fallinlove.finance.repository;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.max.fallinlove.finance.dto.MonthTagAmountDTO;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import com.max.fallinlove.finance.mapper.MonthAmountDetailMapper;
import com.max.fallinlove.finance.mapper.MonthAmountMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rick
 * @date 2022-02-09 15:05
 */
@Repository
public class MonthAmountDetailRepository {

    @Resource
    private MonthAmountMapper monthAmountMapper;
    @Resource
    private MonthAmountDetailMapper monthAmountDetailMapper;

    /**
     * 根据收支类型和月份Id获取每个tag的总费用
     * @param amountType
     * @param monthAmountId
     * @return
     */
    public List<MonthTagAmountDTO> getMonthTagAmountList(String amountType, int monthAmountId) {
        List<MonthAmountDetail> list = monthAmountDetailMapper.queryMonthTagAmount(amountType, monthAmountId);
        final List<MonthTagAmountDTO> result = list.stream().map(monthAmountDetail -> {
            MonthTagAmountDTO dto = new MonthTagAmountDTO();
            BeanUtil.copyProperties(monthAmountDetail, dto);
            return dto;
        }).collect(Collectors.toList());
        return result;
    }
}
