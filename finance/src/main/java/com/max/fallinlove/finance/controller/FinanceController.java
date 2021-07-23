package com.max.fallinlove.finance.controller;

import static com.max.fallinlove.base.constants.RedisConstants.FIN_DISTRIBUTED_LOCK;

import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.common.result.ResultUtils;
import com.max.fallinlove.base.constants.FinanceConstants;
import com.max.fallinlove.finance.cqe.FinanceQuery;
import com.max.fallinlove.finance.dto.FinanceDTO;
import com.max.fallinlove.finance.dto.MonthAmountDTO;
import com.max.fallinlove.finance.entity.Account;
import com.max.fallinlove.finance.entity.MonthAmount;
import com.max.fallinlove.finance.entity.MonthAmountDetail;
import com.max.fallinlove.finance.service.IAccountService;
import com.max.fallinlove.finance.service.IMonthAmountDetailService;
import com.max.fallinlove.finance.service.IMonthAmountService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

//    @Autowired
//    JedisPool jedisPool;
    @Autowired
    IAccountService accountService;
    @Autowired
    IMonthAmountService monthAmountService;
    @Autowired
    IMonthAmountDetailService monthAmountDetailService;

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

        return ResultUtils.success(financeIndexModel);
    }

    @Transactional
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(summary = "添加账单 - 【涂瑜】", tags = {"【账单 模块】账单相关 - 【涂瑜】", "涂瑜"})
    public Result InsertMonthAmountDetail(@RequestBody FinanceQuery insertFinancel) {

//        Jedis jedis = jedisPool.getResource();
//        String clientID = UUID.randomUUID().toString();
//        jedis.set(FIN_DISTRIBUTED_LOCK,clientID, SetParams.setParams().nx().ex(1800));
        //1.更新客户表
        Account account = new Account();
        account.setId(insertFinancel.getId());
        account.setTotalAmount(insertFinancel.getTotalAmount());
        accountService.saveOrUpdate(account);

        //2. 获取月账单 如果没有就设置默认
        MonthAmount monthAmount = monthAmountService.getByTime(insertFinancel.getYear(), insertFinancel.getMonth());
        if (Objects.isNull(monthAmount)) {
            //todo 可以设置成月初自动生成当月账单
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
     * 设置默认月账单
     * @param insertFinancel
     * @return
     */
    private MonthAmount defaultMonthAmount(FinanceQuery insertFinancel) {
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