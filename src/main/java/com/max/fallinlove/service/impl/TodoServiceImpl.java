package com.max.fallinlove.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.max.fallinlove.common.Result;
import com.max.fallinlove.dto.TodoDTO;
import com.max.fallinlove.entity.Todo;
import com.max.fallinlove.mapper.TodoMapper;
import com.max.fallinlove.service.ITodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements ITodoService {

    @Autowired
    TodoMapper todoMapper;
    /**
     * 新增todo
     */
    @Override
    public Result insertTode(TodoDTO todoDTO) {
        Todo todo = new Todo();
        BeanUtil.copyProperties(todoDTO, todo);
        todoMapper.insert(todo);

        return new Result().success();
    }

    /**
     * 删除todo
     */
    @Override
    public Result deleteTode(Integer todoId) {
        todoMapper.deleteById(todoId);

        return new Result().success();
    }

    /**
     * 修改todo
     */
    @Override
    public Result editTode(TodoDTO todoDTO) {
        Todo todo = new Todo();
        BeanUtil.copyProperties(todoDTO, todo);
        todoMapper.updateById(todo);

        return new Result().success();
    }

    /**
     * 查询一个人的todo
     */
    @Override
    public Result selectTode(Integer userId) {
        List<Todo> todoList = todoMapper.selectByUserId(userId);

        return new Result().success(todoList);
    }
}
