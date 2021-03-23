package com.max.fallinlove.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class FinanceIndexModel {

    private int id;

    private BigDecimal totalAmount;

    private List<MonthAmountModel> monthAmountModelList;

}