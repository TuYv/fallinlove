package com.max.fallinlove.finance.service.impl;

import com.max.fallinlove.base.constants.FinanceConstants;
import com.max.fallinlove.finance.req.FinanceReq;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.service.IBillingService;
import com.max.fallinlove.finance.service.IMonthAmountDetailService;
import com.max.fallinlove.finance.service.IMonthAmountService;
import com.max.fallinlove.finance.service.ITagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Rick
 * @date 2022-02-07 17:02
 */
@Service
public class BillingServiceImpl implements IBillingService {

    @Resource IMonthAmountService monthAmountService;
    @Resource IMonthAmountDetailService monthAmountDetailService;
    @Resource ITagService tagService;

    /**
     * 新增记账
     * @param userId
     * @param insertFinance
     * @return
     */
    @Override
    @Transactional
    public Boolean insertMonthAmountDetail(int userId, FinanceReq insertFinance) {
        //1. 更新月账单
        MonthAmount monthAmount = monthAmountService.getByTime(insertFinance.getId(), insertFinance.getYear(), insertFinance.getMonth());

        if (insertFinance.getAmountType().equals(FinanceConstants.AMOUNT_TYPE_INCOME)) {
            monthAmount.setIncome(monthAmount.getIncome().add(insertFinance.getAmount()));
        } else {
            monthAmount.setSpend(monthAmount.getSpend().add(insertFinance.getAmount()));
        }
        monthAmountService.saveOrUpdate(monthAmount);

        //2. 新增本次账单记录
        monthAmountDetailService.saveMonthAmountDetail(monthAmount.getId(), insertFinance);
        //3. tag表新增tag
        tagService.updateByTagName(userId,insertFinance.getTagName());

        return true;
    }
}
