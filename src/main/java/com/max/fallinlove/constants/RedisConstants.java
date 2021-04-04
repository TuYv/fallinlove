package com.max.fallinlove.constants;

/**
 * @program: fall-in-love
 * @description: redis相关的默认值
 * @author: Max.Tu
 * @create: 2021-04-04 22:53
 **/
public class RedisConstants {

    // 通用静态值
    public static final Integer EXPIRE_30_MINUTE = 1800;
    public static final String SET_IF_NOT_EXIST = "nx";
    public static final String SET_WITH_EXPIRE_TIME = "PX";
    // 账单相关静态值
    public static final String FIN_DISTRIBUTED_LOCK = "FIN_DIS_LOCK";
    public static final String TOTLE_AMOUNT  = "TOT:AMT:";

}
