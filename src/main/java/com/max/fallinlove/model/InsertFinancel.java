package com.max.fallinlove.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: eureka-demo
 * @description:
 * @author: Max.Tu
 * @create: 2019-09-04 20:57
 **/
public class InsertFinancel {

    private int id;

    private BigDecimal totalAmount;

    private String month;

    private String year;

    private int monthAmountDetailId;

    private LocalDateTime time;

    private String amountType;

    private BigDecimal amount;

    private String reason;

    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMonthAmountDetailId() {
        return monthAmountDetailId;
    }

    public void setMonthAmountDetailId(int monthAmountDetailId) {
        this.monthAmountDetailId = monthAmountDetailId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
