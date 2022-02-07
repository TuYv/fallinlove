package com.max.fallinlove.finance.service;

import com.max.fallinlove.finance.req.FinanceReq;

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
    Boolean insertMonthAmountDetail(int userId, FinanceReq insertFinance);
}
