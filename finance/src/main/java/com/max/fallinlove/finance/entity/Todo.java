package com.max.fallinlove.finance.entity;

import lombok.Data;

/**
* @author tuy
* @description 
* @create 2022年8月9日 下午9:51:32
*/
@Data
public class Todo {
    /**
     * todo id
     */
    private Integer id;
    /**
     * 
     */
    private Integer userId;
    /**
     * 关系id
     */
    private Integer relationId;
    /**
     * todo内容
     */
    private String content;
    /**
     * 0. 待办  1. 完成
     */
    private Integer status;
}