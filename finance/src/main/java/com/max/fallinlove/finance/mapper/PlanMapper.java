package com.max.fallinlove.finance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.max.fallinlove.finance.entity.Plan;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 计划表 Mapper 接口
 * </p>
 *
 * @author max.tu
 * @since 2022-02-07
 */
public interface PlanMapper extends BaseMapper<Plan> {
    int updateSaved(@Param("accountId")int accountId, @Param("planId") String planId, @Param("amount")
        BigDecimal amount);
}
