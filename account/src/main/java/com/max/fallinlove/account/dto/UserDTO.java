package com.max.fallinlove.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息")
public class UserDTO {

    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("对象id")
    private Integer loverId;

    @ApiModelProperty("账户ID")
    private String accountId;

    @ApiModelProperty("token")
    private String token;
}
