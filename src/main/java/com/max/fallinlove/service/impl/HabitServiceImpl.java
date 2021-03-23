package com.max.fallinlove.service.impl;

import com.max.fallinlove.entity.Habit;
import com.max.fallinlove.mapper.HabitMapper;
import com.max.fallinlove.service.IHabitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2021-03-23
 */
@Service
public class HabitServiceImpl extends ServiceImpl<HabitMapper, Habit> implements IHabitService {

}
