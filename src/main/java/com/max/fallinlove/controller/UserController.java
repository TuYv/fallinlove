package com.max.fallinlove.controller;


import cn.hutool.core.bean.BeanUtil;
import com.max.fallinlove.common.Result;
import com.max.fallinlove.dto.UserDTO;
import com.max.fallinlove.entity.User;
import com.max.fallinlove.mapper.UserMapper;
import com.max.fallinlove.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/max/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "登录")
    @GetMapping("/login")
    public Result login(String userName, String password) {
        return userService.login(userName, password);
    }

    @Operation(summary = "注册")
    @GetMapping("/register")
    public Result register(UserDTO userDTO) {
        return userService.register(userDTO);
    }
}
