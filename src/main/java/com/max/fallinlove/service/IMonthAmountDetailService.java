package com.max.fallinlove.service;

import com.max.fallinlove.entity.MonthAmountDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
public interface IMonthAmountDetailService extends IService<MonthAmountDetail> {
    List<MonthAmountDetail> getMonthAmountDetailList(int monthAmountId);
}
