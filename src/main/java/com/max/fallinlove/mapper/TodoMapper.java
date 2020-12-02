package com.max.fallinlove.mapper;

import com.max.fallinlove.entity.Todo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
public interface TodoMapper extends BaseMapper<Todo> {

    List<Todo> selectByUserId(@Param("userId") Integer userId);

}
