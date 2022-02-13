package com.max.fallinlove.finance.controller;


import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.common.result.ResultUtils;
import com.max.fallinlove.finance.dto.MonthTagAmountDTO;
import com.max.fallinlove.finance.req.FinanceReq;
import com.max.fallinlove.finance.dto.FinanceDTO;
import com.max.fallinlove.finance.dto.MonthAmountDTO;
import com.max.fallinlove.finance.entity.Account;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import com.max.fallinlove.finance.service.*;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: fall-in-love
 * @description:
 * @author: Max.Tu
 * @create: 2021-03-23 22:47
 **/
@Log4j2
@CrossOrigin
@RestController
@Api(tags = "账单相关API")
@RequestMapping("/billing")
public class FinanceController {

    @Resource
    IAccountService accountService;
    @Resource
    IMonthAmountService monthAmountService;
    @Resource
    IMonthAmountDetailService monthAmountDetailService;
    @Resource
    ITagService tagService;
    @Resource IBillingService billingService;

    @GetMapping("/test")
    public Result test() {
        log.info("now in test function");
        return ResultUtils.success("hello");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "账单详情 - 【涂瑜】", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result getFinanceIndex(@PathVariable("id") int id, @RequestHeader("id") int userId) {
        log.info("开始获取账单详情");
        //获取用户账户总金额和每月金额
        Account account = accountService.getById(id);

        List<MonthAmount> monthAmountList = monthAmountService.getAllMonthAmountByAccountId(account.getId());

        //获取用户日金额
        List<MonthAmountDTO> monthAmountModelList = new ArrayList<>();
        //todo 可以一次获取数据后整理 减少数据库访问次数
        monthAmountList.forEach(x -> {
            List<MonthAmountDetail> monthAmountDetailList = monthAmountDetailService.getMonthAmountDetailList(x.getId());
            monthAmountModelList.add(getMonthAmountDTO(x, monthAmountDetailList));
        });
        //整理输出model
        FinanceDTO financeIndexModel = new FinanceDTO();
        financeIndexModel.setId(account.getId());
        financeIndexModel.setTotalAmount(account.getTotalAmount());
        financeIndexModel.setMonthAmountModelList(monthAmountModelList);
        //获取tag列表
        financeIndexModel.setTags(tagService.getTags(userId));

        return ResultUtils.success(financeIndexModel);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(summary = "添加账单 - 【涂瑜】", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result InsertMonthAmountDetail(@RequestHeader("id") int userId, @RequestBody FinanceReq insertFinance) {

//        Jedis jedis = jedisPool.getResource();
//        String clientID = UUID.randomUUID().toString();
//        jedis.set(FIN_DISTRIBUTED_LOCK,clientID, SetParams.setParams().nx().ex(1800));
        Boolean result = billingService.insertMonthAmountDetail(userId, insertFinance);
        return result ? ResultUtils.success() : ResultUtils.fail("0000","新增失败，请重试！");
    }

    @RequestMapping(value = "/monthTag", method = RequestMethod.GET)
    @Operation(summary = "当月tag费用 - 【涂瑜】", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result<List<MonthTagAmountDTO>> MonthTagAmount(@RequestParam("accountId") Integer accountId) {
        List<MonthTagAmountDTO> list = monthAmountDetailService.getMonthTagAmountList(accountId);
        return ResultUtils.success(list);
    }

/*
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result testAmount() {
        LocalDateTime start = LocalDateTime.now();
        Jedis jedis = jedisPool.getResource();
        String clientID = UUID.randomUUID().toString();
        Boolean redisLock = RedisTool.lock(jedis,FIN_DISTRIBUTED_LOCK,clientID,30);
        System.out.println("加锁耗时 " + Duration.between(start,LocalDateTime.now()).toMillis());
        if (redisLock) {
            try {
                Account account = accountService.getById(2);
                account.setTotalAmount(account.getTotalAmount().add(BigDecimal.ONE));
                log.info("总金额： " + account.getTotalAmount());
                accountService.updateById(account);
                System.out.println("SQL耗时 " + Duration.between(start,LocalDateTime.now()).toMillis());
            }finally {
                RedisTool.unlock(jedis,FIN_DISTRIBUTED_LOCK,clientID);
                System.out.println("解锁耗时 " + Duration.between(start,LocalDateTime.now()).toMillis());
            }
        } else {
            log.info("锁占用中");
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("请求耗时：" + Duration.between(start, end).toMillis());

        return ResultUtils.success();
    }*/


    /**
     * 设置月日账单
     * @param monthAmount
     * @param list
     * @return
     */
    private MonthAmountDTO getMonthAmountDTO (MonthAmount monthAmount, List<MonthAmountDetail> list) {
        MonthAmountDTO monthAmountModel = new MonthAmountDTO();
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