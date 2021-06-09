package com.max.fallinlove.account.controller;


import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.account.dto.UserDTO;
import com.max.fallinlove.account.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
@Api(tags = "用户模块")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "登录")
    @GetMapping("/login")
    public Result login( String userName, String password) {
        return userService.login(userName, password);
    }

    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }
}
