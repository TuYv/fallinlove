package com.max.fallinlove.finance;

import com.max.fallinlove.finance.repository.MonthAmountRepository;
import com.max.fallinlove.finance.service.impl.MonthAmountDetailServiceImpl;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinanceApplicationTests {

    @Resource
    private MonthAmountRepository monthAmountRepository;
    @Resource private MonthAmountDetailServiceImpl monthAmountDetailServiceImpl;
    @Test
    void contextLoads() {
        System.out.println(monthAmountDetailServiceImpl.getMonthTagAmountList(1));
    }

}
