package org.apframework.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * https://mp.weixin.qq.com/s/huUW57TV0ZZBMo6VSBJSww
 */
public class CaffeineDemo {

    public static void main(String[] args) {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .initialCapacity(100)//初始大小
                .maximumSize(2)//最大数量
                .expireAfterWrite(3, TimeUnit.SECONDS)//过期时间
                .build();

        cache.put("java金融1", "java金融1");
        cache.put("java金融2", "java金融2");
        cache.put("java金融3", "java金融3");
        cache.cleanUp();

        System.out.println(cache.getIfPresent("java金融1"));
        System.out.println(cache.getIfPresent("java金融2"));
        System.out.println(cache.getIfPresent("java金融3"));
    }

}