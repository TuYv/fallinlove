package com.max.fallinlove.notes.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: fall-in-love
 * @description:
 * @author: Max.Tu
 * @create: 2020-11-09 23:26
 **/
@Data
@ApiModel(value = "todo信息")
public class TodoDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户id", required = true)
    private Integer userId;

    @ApiModelProperty(value = "关系id")
    private Integer relationId;

    @ApiModelProperty(value = "todo 内容", required = true)
    private String content;

    @ApiModelProperty(value = "0. 待办  1. 完成")
    private Integer status;
}
