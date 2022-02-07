package com.max.fallinlove.finance.service;

import com.max.fallinlove.finance.cqe.FinanceQuery;

/**
 * 记账服务
 *
 * @author Rick
 * @date 2022-02-07 17:01
 */
public interface IBillingService {

    /**
     * 新增一笔记账
     * @param userId
     * @param insertFinance
     * @return
     */
    Boolean insertMonthAmountDetail(int userId, FinanceQuery insertFinance);
}
