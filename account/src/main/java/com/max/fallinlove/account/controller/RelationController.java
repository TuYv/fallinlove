package com.max.fallinlove.account.controller;


import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.account.service.IRelationService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
@Api(tags = "关系模块")
@RestController
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    IRelationService relationService;

    @Operation(summary = "设置关系")
    @PostMapping("/setRelation")
    public Result setRelation(Integer userId, Integer toId){
        return relationService.setRelation(userId, toId);
    }
}
