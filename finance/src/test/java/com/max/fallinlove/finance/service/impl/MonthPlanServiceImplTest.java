package com.max.fallinlove.finance.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rick
 * @date 2022-03-02 14:12
 */
@SpringBootTest
class MonthPlanServiceImplTest {

    @Resource MonthPlanServiceImpl monthPlanServiceImpl;

    @Test void queryMonthPlan() {
        System.out.println(monthPlanServiceImpl.queryMonthPlan(1));
    }
}