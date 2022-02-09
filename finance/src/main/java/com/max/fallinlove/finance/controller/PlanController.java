package com.max.fallinlove.finance.controller;

import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.common.result.ResultUtils;
import com.max.fallinlove.finance.dto.MonthPlanDTO;
import com.max.fallinlove.finance.dto.PlanDTO;
import com.max.fallinlove.finance.req.JoinPlanReq;
import com.max.fallinlove.finance.req.MonthPlanReq;
import com.max.fallinlove.finance.req.PlanReq;
import com.max.fallinlove.finance.service.IMonthPlanService;
import com.max.fallinlove.finance.service.IPlanService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 预算接口
 *
 * @author Rick
 * @date 2022-02-07 18:56
 */
@Slf4j
@CrossOrigin
@RestController
@Api(tags = "预算相关API")
@RequestMapping("/plan")
public class PlanController {

    @Resource private IPlanService planService;
    @Resource private IMonthPlanService monthPlanService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test() {
        log.info("now in test function");
        return ResultUtils.success("hello from plan controller");
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(summary = "新增计划 - 【涂瑜】", tags = {"【预算 模块】预算相关 - 【涂瑜】", "涂瑜"})
    public Result insert(@RequestBody PlanReq planReq) {
        planService.savePlan(planReq);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @Operation(summary = "加入计划 - 【涂瑜】", tags = {"【预算 模块】预算相关 - 【涂瑜】", "涂瑜"})
    public Result join(@RequestBody JoinPlanReq joinPlanReq) {
        planService.joinPlan(joinPlanReq);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @Operation(summary = "查询计划 - 【涂瑜】", tags = {"【预算 模块】预算相关 - 【涂瑜】", "涂瑜"})
    public Result<List<PlanDTO>> query(@RequestParam(value = "accountId") Integer accountId) {
        List<PlanDTO> result = planService.queryPlanList(accountId);
        return ResultUtils.success(result);
    }

    @RequestMapping(value = "/amount/add", method = RequestMethod.POST)
    @Operation(summary = "转入金额 - 【涂瑜】", tags = {"【预算 模块】预算相关 - 【涂瑜】", "涂瑜"})
    public Result<List<PlanDTO>> addAmout(@RequestParam(value = "accountId") Integer accountId,
                                          @RequestParam(value = "planId") String planId,
                                          @RequestParam(value = "amount") BigDecimal amount) {
        planService.addAmount(accountId, planId, amount);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/monthPlan/insert", method = RequestMethod.POST)
    @Operation(summary = "新增预算 - 【涂瑜】", tags = {"【预算 模块】预算相关 - 【涂瑜】", "涂瑜"})
    public Result monthPlanInsert(@RequestBody MonthPlanReq req) {
        monthPlanService.saveMonthPlan(req);
        return ResultUtils.success();
    }

    @RequestMapping(value = "/monthPlan/query", method = RequestMethod.GET)
    @Operation(summary = "查询预算 - 【涂瑜】", tags = {"【预算 模块】预算相关 - 【涂瑜】", "涂瑜"})
    public Result<List<MonthPlanDTO>> monthPlanQuery(@RequestParam(value = "accountId") Integer accountId) {
        final List<MonthPlanDTO> result = monthPlanService.queryMonthPlan(accountId);
        return ResultUtils.success(result);
    }
}
