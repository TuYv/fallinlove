package com.max.fallinlove.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: fall-in-love
 * @description:
 * @author: Max.Tu
 * @create: 2021-04-04 21:33
 **/
@SpringBootTest
public class JedisPoolTests {

    @Autowired
    JedisPool jedisPool;

    @Test
    void jedis() {
        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "zhangshan");
        String name = jedis.get("name");

        System.out.println(name);
        jedis.close();
    }

}
