package com.max.fallinlove.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author max.tu
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * todo id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 关系id
     */
    private Integer relationId;

    /**
     * todo 内容
     */
    private String content;

    /**
     * 0. 待办  1. 完成
     */
    private Integer status;


}
