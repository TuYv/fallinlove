package com.max.fallinlove.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fall-in-love
 * @description:
 * @author: Max.Tu
 * @create: 2020-11-03 17:03
 **/
@Api(tags = "测试")
@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "测试接口")
    @GetMapping("/test")
    public String test() {
        return "success!!";
    }

}
