package com.max.fallinlove.model;

import com.max.fallinlove.entity.MonthAmountDetail;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: eureka-demo
 * @description:
 * @author: Max.Tu
 * @create: 2019-09-04 15:06
 **/
public class MonthAmountModel {

    private int id;

    private int accountId;

    private String time;

    private BigDecimal income;

    private BigDecimal spend;

    private List<MonthAmountDetail>  monthAmountDetailList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getSpend() {
        return spend;
    }

    public void setSpend(BigDecimal spend) {
        this.spend = spend;
    }

    public List<MonthAmountDetail> getMonthAmountDetailList() {
        return monthAmountDetailList;
    }

    public void setMonthAmountDetailList(List<MonthAmountDetail> monthAmountDetailList) {
        this.monthAmountDetailList = monthAmountDetailList;
    }
}
