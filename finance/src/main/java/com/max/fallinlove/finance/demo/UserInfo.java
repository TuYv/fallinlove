package com.max.fallinlove.finance.demo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Max.Tu
 * @program common
 * @description
 * @create 2022-03-20 17:48
 **/
@Data
@Accessors(chain = true)
public class UserInfo {
    private String username;
    private String userid;
    private String state;
}
