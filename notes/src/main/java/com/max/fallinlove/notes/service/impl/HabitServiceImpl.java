package com.max.fallinlove.notes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.max.fallinlove.notes.entity.Habit;
import com.max.fallinlove.notes.mapper.HabitMapper;
import com.max.fallinlove.notes.service.IHabitService;
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
