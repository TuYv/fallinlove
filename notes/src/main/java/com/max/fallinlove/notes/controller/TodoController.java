package com.max.fallinlove.notes.controller;


import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.notes.dto.TodoDTO;
import com.max.fallinlove.notes.service.ITodoService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
@Api(tags = "todo列表")
@RestController
@RequestMapping("/max/todo")
public class TodoController {

    @Autowired
    ITodoService todoService;

    @Operation(summary = "新增todo")
    @PostMapping("/insert")
    public Result insert(@RequestBody TodoDTO todo) {
        return todoService.insertTode(todo);
    }

    @Operation(summary = "删除todo")
    @GetMapping("/delete")
    public Result delete(@RequestParam("todoId") Integer todoId) {
        return todoService.deleteTode(todoId);
    }

    @Operation(summary = "修改todo")
    @PostMapping("/edit")
    public Result edit(@RequestBody TodoDTO todo) {
        return todoService.editTode(todo);
    }

    @Operation(summary = "查询todo列表")
    @GetMapping("/list")
    public Result list(Integer userId) {
        return todoService.selectTode(userId);
    }
}
