package com.max.fallinlove.base.utils;

import com.max.fallinlove.base.constants.IntegerConstants;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Rick.Tu
 * @program fallinlove
 * @description 日期工具
 * @create 2023-07-06 17:18
 **/
public class DateUtil {

    //获取 一天开始的日期
    public static Date getDayStartTime(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天结束的日期
     */
    public static Date getDayEndTime(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 返回这个月有多少天
     * @param year
     * @param month
     * @return
     */
    public static Integer getMonthDay(Integer year, Integer month) {
        LocalDate date = LocalDate.of(year, month, IntegerConstants.ONE);
        return date.getDayOfMonth();
    }
}
