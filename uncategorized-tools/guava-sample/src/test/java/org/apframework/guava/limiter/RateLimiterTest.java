package org.apframework.guava.limiter;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

    public static void main(String[] args) {
        //create方法传入的是每秒生成令牌的个数
        RateLimiter rateLimiter = RateLimiter.create(1.0);
        for (int i = 0; i < 5; i++) {
            /**
             * 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
             * 当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
             * 当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个请求
             */
            double waitTime = rateLimiter.acquire(1);
            System.out.println(System.currentTimeMillis() / 1000 + " , " + waitTime);
        }
    }
}
