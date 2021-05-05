package com.max.fallinlove.finance.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class FinanceDTO {

    private int id;

    private BigDecimal totalAmount;

    private List<MonthAmountDTO> monthAmountModelList;

}