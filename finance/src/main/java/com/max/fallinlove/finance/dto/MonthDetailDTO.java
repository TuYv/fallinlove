package com.max.fallinlove.finance.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * @author Rick.Tu
 * @program fallinlove
 * @description 月详情DTO
 * @create 2023-07-06 18:20
 **/
@Data
public class MonthDetailDTO {
    /**
     * 月总收入
     */
    public BigDecimal income;
    /**
     * 月总支出
     */
    public BigDecimal spend;

    public List<TagDetailDTO> tagDetailDTOList;
}
