package com.max.fallinlove.account.entity;

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
public class Relation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关系id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户1的id
     */
    private Integer user1Id;

    /**
     * 用户2的id
     */
    private Integer user2Id;


}
