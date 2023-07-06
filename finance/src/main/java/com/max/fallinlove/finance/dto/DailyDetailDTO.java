package com.max.fallinlove.finance.dto;

import com.max.fallinlove.finance.entity.MonthAmountDetail;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * @author Rick.Tu
 * @program fallinlove
 * @description
 * @create 2023-07-06 16:38
 **/
@Data
public class DailyDetailDTO {
    private BigDecimal income;
    private BigDecimal spend;
    private List<MonthAmountDetail> detailList;
}
