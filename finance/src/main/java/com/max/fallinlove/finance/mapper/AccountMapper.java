package com.max.fallinlove.finance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.fallinlove.finance.entity.Account;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
public interface AccountMapper extends BaseMapper<Account> {

    int updateAmountById(@Param("id") int id, @Param("amount") BigDecimal amount);
}
