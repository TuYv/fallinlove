package com.max.fallinlove.finance.dto;

import com.max.fallinlove.base.constants.IntegerConstants;
import lombok.Data;

/**
 * @author Rick.Tu
 * @program fallinlove
 * @description 记账汇总DTO
 * @create 2023-07-06 16:20
 **/
@Data
public class AggregateBillingDTO {

    /**
     * 账号Id
     */
    private Integer accountId;
    /**
     * 总记账数量
     */
    private Integer totalCount;
    /**
     * 总记账天数
     */
    private Integer totalDays;

    public static AggregateBillingDTO build(Integer accountId) {
        AggregateBillingDTO dto = new AggregateBillingDTO();
        dto.setAccountId(accountId);
        dto.setTotalCount(IntegerConstants.ZERO);
        dto.setTotalDays(IntegerConstants.ZERO);
        return dto;
    }

}
