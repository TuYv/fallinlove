package com.max.fallinlove.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
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

    /**
     * 获取当前月的tag费用
     * @return
     */
    List<MonthAmountDetail> getMonthTagAmountList();
}
