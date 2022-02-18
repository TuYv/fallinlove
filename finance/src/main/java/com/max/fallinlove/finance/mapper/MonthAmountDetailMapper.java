package com.max.fallinlove.finance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.fallinlove.finance.dto.MonthPlanDTO;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
public interface MonthAmountDetailMapper extends BaseMapper<MonthAmountDetail> {

    List<MonthAmountDetail> queryMonthTagAmount(@Param("amountType")String amountType, @Param("monthAmountId") Integer monthAmountId);

    List<MonthPlanDTO> queryUsedAmountForMonthPlan( @Param("monthAmountId")Integer monthAmountId);
}
