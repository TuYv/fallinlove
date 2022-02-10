package com.max.fallinlove.finance.service.impl;

import com.max.fallinlove.base.constants.FinanceConstants;
import com.max.fallinlove.finance.req.FinanceReq;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.service.IAccountService;
import com.max.fallinlove.finance.service.IBillingService;
import com.max.fallinlove.finance.service.IMonthAmountDetailService;
import com.max.fallinlove.finance.service.IMonthAmountService;
import com.max.fallinlove.finance.service.ITagService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Rick
 * @date 2022-02-07 17:02
 */
@Service
public class BillingServiceImpl implements IBillingService {

    @Resource private IAccountService accountService;
    @Resource private IMonthAmountService monthAmountService;
    @Resource private IMonthAmountDetailService monthAmountDetailService;
    @Resource private ITagService tagService;

    /**
     * 新增记账
     * @param accountId
     * @param insertFinance
     * @return
     */
    @Override
    @Transactional
    public Boolean insertMonthAmountDetail(int accountId, FinanceReq insertFinance) {
        //1. 更新余额
        BigDecimal amount = insertFinance.getAmount();
        //2. 更新月账单
        MonthAmount monthAmount = monthAmountService.getByTime(insertFinance.getId(), insertFinance.getYear(), insertFinance.getMonth());

        if (insertFinance.getAmountType().equals(FinanceConstants.AMOUNT_TYPE_INCOME)) {
            monthAmount.setIncome(monthAmount.getIncome().add(insertFinance.getAmount()));
        } else {
            monthAmount.setSpend(monthAmount.getSpend().add(insertFinance.getAmount()));
            amount = amount.negate();
        }
        accountService.updateAmountById(accountId, amount);
        monthAmountService.saveOrUpdate(monthAmount);

        //3. 新增本次账单记录
        monthAmountDetailService.saveMonthAmountDetail(monthAmount.getId(), insertFinance);
        //4. tag表新增tag
        tagService.updateByTagName(accountId,insertFinance.getTagName());

        return true;
    }
}
