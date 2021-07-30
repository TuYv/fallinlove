package com.max.fallinlove.finance.dto;

import java.math.BigDecimal;
import java.util.List;

import com.max.fallinlove.finance.entity.Tag;
import lombok.Data;

@Data
public class FinanceDTO {

    private int id;

    private BigDecimal totalAmount;

    private List<MonthAmountDTO> monthAmountModelList;

    private List<Tag> tags;

}