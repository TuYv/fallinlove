package com.max.fallinlove.controller;

import com.max.fallinlove.common.Result;
import com.max.fallinlove.common.ResultUtils;
import com.max.fallinlove.constants.FinanceConstants;
import com.max.fallinlove.entity.Account;
import com.max.fallinlove.entity.MonthAmount;
import com.max.fallinlove.entity.MonthAmountDetail;
import com.max.fallinlove.model.FinanceIndexModel;
import com.max.fallinlove.model.InsertFinancel;
import com.max.fallinlove.model.MonthAmountModel;
import com.max.fallinlove.service.IAccountService;
import com.max.fallinlove.service.IMonthAmountDetailService;
import com.max.fallinlove.service.IMonthAmountService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fall-in-love
 * @description:
 * @author: Max.Tu
 * @create: 2021-03-23 22:47
 **/
@CrossOrigin
@RestController
@Api(tags = "账单相关API")
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IMonthAmountService monthAmountService;

    @Autowired
    IMonthAmountDetailService monthAmountDetailService;

    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
    @Operation(summary = "账单详情 - 【涂瑜】", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result<FinanceIndexModel> getFinanceIndex(@PathVariable("id") int id) {

        //获取用户账户总金额和每月金额
        Account account = accountService.getById(id);
        List<MonthAmount> monthAmountList = monthAmountService.getAllMonthAmountByAccountId(account.getId());

        //获取用户日金额
        List<MonthAmountModel> monthAmountModelList = new ArrayList<>();
        //todo 可以一次获取数据后整理 减少数据库访问次数
        monthAmountList.forEach(x -> {
            List<MonthAmountDetail> monthAmountDetailList = monthAmountDetailService.getMonthAmountDetailList(x.getId());
            monthAmountModelList.add(getMonthAmountModel(x, monthAmountDetailList));
        });
        //整理输出model
        FinanceIndexModel financeIndexModel = new FinanceIndexModel();
        financeIndexModel.setId(account.getId());
        financeIndexModel.setTotalAmount(account.getTotalAmount());
        financeIndexModel.setMonthAmountModelList(monthAmountModelList);

        return ResultUtils.success(financeIndexModel);
    }

    @Transactional
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(summary = "添加账单 - 【涂瑜】", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result InsertMonthAmountDetail(@RequestBody InsertFinancel insertFinancel) {

        //1.更新客户表
        Account account = new Account();
        account.setId(insertFinancel.getId());
        account.setTotalAmount(insertFinancel.getTotalAmount());
        accountService.saveOrUpdate(account);

        //2. 获取月账单 如果没有就设置默认
        MonthAmount monthAmount = monthAmountService.getByTime(insertFinancel.getYear(), insertFinancel.getMonth());
        if (Objects.isNull(monthAmount)) {
            monthAmount = defaultMonthAmount(insertFinancel);
        }
        if (insertFinancel.getAmountType().equals(FinanceConstants.AMOUNT_TYPE_INCOME)) {
            monthAmount.setIncome(monthAmount.getIncome().add(insertFinancel.getAmount()));
        } else {
            monthAmount.setSpend(monthAmount.getSpend().add(insertFinancel.getAmount()));
        }
        monthAmountService.saveOrUpdate(monthAmount);

        //3. 新增本次账单记录
        MonthAmountDetail monthAmountDetail = new MonthAmountDetail();
        monthAmountDetail.setMonthAmountId(monthAmount.getId());
        monthAmountDetail.setAmount(insertFinancel.getAmount());
        monthAmountDetail.setAmountType(insertFinancel.getAmountType());
        monthAmountDetail.setTime(insertFinancel.getTime());
        monthAmountDetail.setReason(insertFinancel.getReason());
        monthAmountDetail.setRemark(insertFinancel.getRemark());
        monthAmountDetailService.save(monthAmountDetail);

        return ResultUtils.success();
    }

    /**
     * 设置默认月账单
     * @param insertFinancel
     * @return
     */
    private MonthAmount defaultMonthAmount(InsertFinancel insertFinancel) {
        MonthAmount monthAmount = new MonthAmount();
        monthAmount.setMonth(insertFinancel.getMonth());
        monthAmount.setAccountId(insertFinancel.getId());
        monthAmount.setYear(insertFinancel.getYear());
        monthAmount.setSpend(BigDecimal.ZERO);
        monthAmount.setIncome(BigDecimal.ZERO);

        return monthAmount;
    }

    /**
     * 设置月日账单
     * @param monthAmount
     * @param list
     * @return
     */
    private MonthAmountModel getMonthAmountModel (MonthAmount monthAmount, List<MonthAmountDetail> list) {
        MonthAmountModel monthAmountModel = new MonthAmountModel();
        monthAmountModel.setId(monthAmount.getId());
        monthAmountModel.setAccountId(monthAmount.getAccountId());
        monthAmountModel.setTime(monthAmount.getYear() + "." + monthAmount.getMonth());
        monthAmountModel.setIncome(monthAmount.getIncome());
        monthAmountModel.setSpend(monthAmount.getSpend());

        monthAmountModel.setMonthAmountDetailList(list.stream().sorted(Comparator.comparing(MonthAmountDetail::getTime).reversed()).collect(
            Collectors.toList()));

        return monthAmountModel;
    }
}