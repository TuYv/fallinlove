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
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FinanceController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IMonthAmountService monthAmountService;

    @Autowired
    IMonthAmountDetailService monthAmountDetailService;

    @RequestMapping(value = "/finance/index/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "账单详情 - 【涂瑜】", notes = " ", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result<FinanceIndexModel> getFinanceIndex(@PathVariable("id") int id) {

        Account account = accountService.getById(id);
        List<MonthAmount> monthAmountList = monthAmountService.getAllMonthAmountByAccountId(account.getId());

        FinanceIndexModel financeIndexModel = new FinanceIndexModel();
        List<MonthAmountModel> monthAmountModelList = new ArrayList<>();
        monthAmountList.forEach(x -> {
            List<MonthAmountDetail> monthAmountDetailList = monthAmountDetailService.getMonthAmountDetailList(x.getId());
            MonthAmountModel monthAmountModel = new MonthAmountModel();
            monthAmountModel.setId(x.getId());
            monthAmountModel.setAccountId(x.getAccountId());
            monthAmountModel.setTime(x.getMonth());
            monthAmountModel.setIncome(x.getIncome());
            monthAmountModel.setSpend(x.getSpend());
            monthAmountModel.setMonthAmountDetailList(monthAmountDetailList);
            monthAmountModelList.add(monthAmountModel);
        });
        financeIndexModel.setId(account.getId());
        financeIndexModel.setTotalAmount(account.getTotalAmount());
        financeIndexModel.setMonthAmountModelList(monthAmountModelList);

        return ResultUtils.success(financeIndexModel);
    }

    @RequestMapping(value = "/finance/insert", method = RequestMethod.PUT)
    @ApiOperation(value = "添加账单 - 【涂瑜】", notes = " ", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result InsertMonthAmountDetail(@RequestBody InsertFinancel insertFinancel) {
        Account account = new Account();
        account.setId(insertFinancel.getId());
        account.setTotalAmount(insertFinancel.getTotalAmount());
        accountService.saveOrUpdate(account);

        MonthAmount monthAmount = monthAmountService.getByTime(insertFinancel.getYear(), insertFinancel.getMonth());
        if("1".equals(insertFinancel.getAmountType())) {
            monthAmount.setIncome(monthAmount.getIncome().add(insertFinancel.getAmount()));
        } else if ("0".equals(insertFinancel.getAmountType())) {
            monthAmount.setSpend(monthAmount.getSpend().add(insertFinancel.getAmount()));
        } else {
            //todo  异常处理
        }
        monthAmountService.updateById(monthAmount);

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