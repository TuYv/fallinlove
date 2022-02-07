package com.max.fallinlove.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.finance.entity.Plan;
import com.max.fallinlove.finance.mapper.PlanMapper;
import com.max.fallinlove.finance.service.IPlanService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 计划表 服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2022-02-07
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements IPlanService {

}
