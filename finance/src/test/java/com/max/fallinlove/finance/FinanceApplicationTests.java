package com.max.fallinlove.finance;

import com.max.fallinlove.common.tools.RedisUtils;
import com.max.fallinlove.finance.repository.MonthAmountRepository;
import com.max.fallinlove.finance.service.impl.MonthAmountDetailServiceImpl;
import javax.annotation.Resource;

import com.max.fallinlove.finance.service.impl.MonthPlanServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinanceApplicationTests {

    @Resource
    private MonthAmountRepository monthAmountRepository;
    @Resource
    private RedisUtils redisUtils;
    @Resource private MonthAmountDetailServiceImpl monthAmountDetailServiceImpl;
    @Resource private MonthPlanServiceImpl monthPlanServiceImpl;
    @Test
    void contextLoads() {
        //redisUtils.set("test", "234");
        System.out.println(redisUtils.rangeZset("2",0, -1));
        //System.out.println(monthAmountDetailServiceImpl.getMonthTagAmountList(1));
    }

    @Test
    void queryMonthPlan() {
        System.out.println(monthPlanServiceImpl.queryMonthPlan(1));
    }

}
