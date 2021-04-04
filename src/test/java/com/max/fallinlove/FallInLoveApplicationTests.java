package com.max.fallinlove;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class FallInLoveApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void jedis() {
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("name", "zhangshan");
        String name = jedis.get("name");

        System.out.println(name);
        jedis.close();
    }

}
