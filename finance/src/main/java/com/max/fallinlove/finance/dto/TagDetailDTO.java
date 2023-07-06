package com.max.fallinlove.finance.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author Rick.Tu
 * @program fallinlove
 * @description 标签详情DTO
 * @create 2023-07-06 18:21
 **/
@Data
public class TagDetailDTO {

    private String tagCode;
    private String tagName;
    private String amountType;
    /**
     * 标签总金额
     */
    public BigDecimal amount;
}
