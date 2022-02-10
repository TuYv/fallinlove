package com.max.fallinlove.finance.repository;

import com.max.fallinlove.finance.mapper.AccountMapper;
import java.math.BigDecimal;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author Max.Tu
 * @program common
 * @description 账户仓储服务
 * @create 2022-02-10 23:05
 **/
@Repository
public class AccountRepository {

    @Resource private AccountMapper accountMapper;

    public int updateAmountById(int id, BigDecimal amount) {
        return accountMapper.updateAmountById(id, amount);
    }
}
