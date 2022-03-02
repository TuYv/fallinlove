package com.max.fallinlove.finance.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rick
 * @date 2022-03-02 14:08
 */

@SpringBootTest
class MonthAmountRepositoryTest {

    @Resource MonthAmountRepository monthAmountRepository;

    @Test void getByTime() {
        System.out.println(monthAmountRepository.getByTime(1, "2022", "03"));
    }
}