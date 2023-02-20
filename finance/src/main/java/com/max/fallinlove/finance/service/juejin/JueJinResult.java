package com.max.fallinlove.finance.service.juejin;

import java.util.List;
import lombok.Data;

/**
 * @author Rick.Tu
 * @program fallinlove
 * @description
 * @create 2023-02-20 10:51
 **/
@Data
public class JueJinResult {
    private Integer err_no;

    private String err_msg;

    private List<BugNotCollectionRes> data;

}
