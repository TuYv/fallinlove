package com.max.fallinlove.finance.dto;

import com.max.fallinlove.base.constants.IntegerConstants;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author Rick.Tu
 * @program fallinlove
 * @description 月每日数量DTO
 * @create 2023-07-31 21:59
 **/
@Data
public class MonthDailyCountDTO {

    /**
     * 月对应日
     */
    private Integer day;
    /**
     * 记账数量
     */
    private Integer count;

    public static List<MonthDailyCountDTO> buildMonthDayCount(Integer monthDay) {
        List<MonthDailyCountDTO> monthDayCountList = new ArrayList<>();
        for (int i = 1; i <= monthDay; i++) {
            monthDayCountList.add(MonthDailyCountDTO.defaultDTO(i));
        }
        return monthDayCountList;
    }

    public static MonthDailyCountDTO defaultDTO(Integer day) {
        MonthDailyCountDTO countDTO = new MonthDailyCountDTO();
        countDTO.setDay(day);
        countDTO.setCount(IntegerConstants.ZERO);
        return countDTO;
    }

    public void add() {
        count++;
    }
}
