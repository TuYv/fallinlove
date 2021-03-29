package com.max.fallinlove.controller;

import com.max.fallinlove.common.Result;
import com.max.fallinlove.common.ResultUtils;
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

        Account account = accountService.getById(id);
        List<MonthAmount> monthAmountList = monthAmountService.getAllMonthAmountByAccountId(account.getId());

        FinanceIndexModel financeIndexModel = new FinanceIndexModel();
        List<MonthAmountModel> monthAmountModelList = new ArrayList<>();
        monthAmountList.forEach(x -> {
            List<MonthAmountDetail> monthAmountDetailList = monthAmountDetailService.getMonthAmountDetailList(x.getId());
            monthAmountDetailList = monthAmountDetailList.stream().sorted(Comparator.comparing(MonthAmountDetail::getTime).reversed()).collect(
                Collectors.toList());
            MonthAmountModel monthAmountModel = new MonthAmountModel();
            monthAmountModel.setId(x.getId());
            monthAmountModel.setAccountId(x.getAccountId());
            monthAmountModel.setTime(x.getYear() + "." + x.getMonth());
            monthAmountModel.setIncome(x.getIncome());
            monthAmountModel.setSpend(x.getSpend());
            monthAmountModel.setMonthAmountDetailList(monthAmountDetailList);
            monthAmountModelList.add(monthAmountModel);
        });
        financeIndexModel.setId(account.getId());
        financeIndexModel.setTotalAmount(account.getTotalAmount());
//        monthAmountModelList = monthAmountModelList.stream().sorted(Comparator.comparing(MonthAmountModel::getTime).reversed()).collect(
//            Collectors.toList());
        financeIndexModel.setMonthAmountModelList(monthAmountModelList);

        return ResultUtils.success(financeIndexModel);
    }

    @Transactional
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(summary = "添加账单 - 【涂瑜】", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result InsertMonthAmountDetail(@RequestBody InsertFinancel insertFinancel) {
        Account account = new Account();
        account.setId(insertFinancel.getId());
        account.setTotalAmount(insertFinancel.getTotalAmount());
        accountService.saveOrUpdate(account);

        MonthAmount monthAmount = monthAmountService.getByTime(insertFinancel.getYear(), insertFinancel.getMonth());
        if (monthAmount != null) {
            if("1".equals(insertFinancel.getAmountType())) {
                monthAmount.setIncome(monthAmount.getIncome().add(insertFinancel.getAmount()));
            } else if ("0".equals(insertFinancel.getAmountType())) {
                monthAmount.setSpend(monthAmount.getSpend().add(insertFinancel.getAmount()));
            } else {
                //todo  异常处理
            }
        } else {
            monthAmount = new MonthAmount();
            monthAmount.setMonth(insertFinancel.getMonth());
            monthAmount.setAccountId(insertFinancel.getId());
            monthAmount.setYear(insertFinancel.getYear());
            if("1".equals(insertFinancel.getAmountType())) {
                monthAmount.setIncome(insertFinancel.getAmount());
                monthAmount.setSpend(BigDecimal.ZERO);
            } else if ("0".equals(insertFinancel.getAmountType())) {
                monthAmount.setIncome(BigDecimal.ZERO);
                monthAmount.setSpend(insertFinancel.getAmount());
            } else {
                //todo  异常处理
            }
        }
        monthAmountService.saveOrUpdate(monthAmount);

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
}