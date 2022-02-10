package com.max.fallinlove.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.finance.entity.Account;
import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
public interface IAccountService extends IService<Account> {

    int updateAmountById(int id, BigDecimal amount);
}
