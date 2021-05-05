package com.max.fallinlove.notes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.notes.dto.TodoDTO;
import com.max.fallinlove.notes.entity.Todo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
public interface ITodoService extends IService<Todo> {

    /**
     * 新增todo
     * @param todo
     * @return
     */
    Result insertTode(TodoDTO todo);

    /**
     * 删除todo
     * @param todoId
     * @return
     */
    Result deleteTode(Integer todoId);

    /**
     * 修改todo
     * @param todo
     * @return
     */
    Result editTode(TodoDTO todo);

    /**
     * 查询一个人的todo
     * @param userId
     * @return
     */
    Result selectTode(Integer userId);

}
