package com.max.fallinlove.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.finance.dto.AggregateBillingDTO;
import com.max.fallinlove.finance.dto.DailyDetailDTO;
import com.max.fallinlove.finance.dto.MonthTagAmountDTO;
import com.max.fallinlove.finance.req.FinanceReq;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import java.util.Date;
import java.util.List;

/**
 *  服务类
 *
 * @author max.tu
 * @since 2021-03-23
 */
public interface IMonthAmountDetailService extends IService<MonthAmountDetail> {

    /**
     * 保存记账详情
     * @param id
     * @param insertFinance
     */
    void saveMonthAmountDetail(int id, FinanceReq insertFinance);

    List<MonthAmountDetail> getMonthAmountDetailList(int monthAmountId);

    /**
     * 获取当前月的tag费用
     * @return
     */
    List<MonthTagAmountDTO> getMonthTagAmountList(Integer accountId);

    /**
     * 获取一个用户的所有记录详情
     * @param accountId
     * @return
     */
    List<MonthAmountDetail> getUserMonthAmountDetail(int accountId);

    AggregateBillingDTO getAggregateInfo(Integer accountId);

    DailyDetailDTO getDailyDetail(Date date, Integer accountId);
}
