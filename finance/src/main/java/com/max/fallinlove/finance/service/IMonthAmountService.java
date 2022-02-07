package com.max.fallinlove.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.finance.entity.MonthAmount;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
public interface IMonthAmountService extends IService<MonthAmount> {
    List<MonthAmount> getAllMonthAmountByAccountId(int accountId);

    /**
     * 根据时间获取月消费信息
     * @param id
     * @param year
     * @param month
     * @return
     */
    MonthAmount getByTime(Integer id, String year, String month);
}
