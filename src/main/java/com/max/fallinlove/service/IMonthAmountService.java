package com.max.fallinlove.service;

import com.max.fallinlove.entity.MonthAmount;
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
public interface IMonthAmountService extends IService<MonthAmount> {
    List<MonthAmount> getAllMonthAmountByAccountId(int accountId);

    MonthAmount getByTime(String year, String month);
}
