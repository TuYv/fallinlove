//package com.max.fallinlove.common;
//
//import java.util.Collections;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.params.SetParams;
//
//public class RedisTool {
//
//    private static final Long RELEASE_SUCCESS = 1L;
//    private static final String LOCK_SUCCESS = "OK";
//
//    /**
//     * 尝试获取分布式锁
//     * @param jedis Redis客户端
//     * @param lockKey 锁
//     * @param requestId 请求标识
//     * @param expireTime 超期时间
//     * @return 是否获取成功
//     */
//    public static boolean lock(Jedis jedis, String lockKey, String requestId, int expireTime) {
//        String result = jedis.set(lockKey, requestId, SetParams.setParams().nx().ex(expireTime));
//        return LOCK_SUCCESS.equals(result);
//    }
//
//    /**
//     * 释放分布式锁
//     * @param jedis Redis客户端
//     * @param lockKey 锁
//     * @param requestId 请求标识
//     * @return 是否释放成功
//     */
//    public static boolean unlock(Jedis jedis, String lockKey, String requestId) {
//
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
//        return RELEASE_SUCCESS.equals(result);
//
//    }
//
//}