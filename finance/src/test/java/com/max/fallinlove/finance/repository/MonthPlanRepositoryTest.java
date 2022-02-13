package com.max.fallinlove.finance.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MonthPlanRepositoryTest {

    @Resource MonthPlanRepository monthPlanRepository;

    @Test
    void queryMonthPlanList() {
        System.out.println(monthPlanRepository.queryMonthPlanList(1));
    }
}