package com.max.fallinlove.finance.controller;

import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.common.result.ResultUtils;
import com.max.fallinlove.finance.req.PlanReq;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test() {
        log.info("now in test function");
        return ResultUtils.success("hello from plan controller");
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insert(@RequestBody PlanReq planReq) {

    }
}
