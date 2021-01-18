package org.apframework.mock.redis;

import ai.grakn.redismock.RedisServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTestApplicationTests {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static RedisServer redisServer = null;

    @BeforeClass
    public static void init() {
        try {
            redisServer = RedisServer.newRedisServer();
            redisServer.start();
            //因为RedisServer启动的端口无法预知（如果写死的话就容易有端口冲突），所以需要实现动态端口配置。
            System.setProperty("spring.redis.port", Integer.toString(redisServer.getBindPort()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public static void close() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    @Test
    public void testRedis() {
        String name = stringRedisTemplate.opsForValue().get("name");
        Assert.assertEquals("tenmao", name);
    }
}
