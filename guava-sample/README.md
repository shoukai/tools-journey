# Guava

## 限流

### 分类

限流常见的算法有计数器法、漏桶算法与令牌桶算法。

#### 计数器法

原理：在单位时间段内，对请求数进行计数，如果数量超过了单位时间的限制，则执行限流策略，当单位时间结束后，计数器清零，这个过程周而复始，就是计数器法。

缺点：不能均衡限流，在一个单位时间的末尾和下一个单位时间的开始，很可能会有两个访问的峰值，导致系统崩溃。

改进方式：可以通过减小单位时间来提高精度。

#### 漏桶算法

原理：假设有一个水桶，水桶有一定的容量，所有请求不论速度都会注入到水桶中，然后水桶以一个恒定的速度向外将请求放出，当水桶满了的时候，新的请求被丢弃。  

优点：可以平滑请求，削减峰值。  

缺点：瓶颈会在漏出的速度，可能会拖慢整个系统，且不能有效地利用系统的资源。  

#### 令牌桶算法

原理：有一个令牌桶，单位时间内令牌会以恒定的数量（即令牌的加入速度）加入到令牌桶中，所有请求都需要获取令牌才可正常访问。当令牌桶中没有令牌可取的时候，则拒绝请求。  

优点：相比漏桶算法，令牌桶算法允许一定的突发流量，但是又不会让突发流量超过我们给定的限制（单位时间窗口内的令牌数）。即限制了我们所说的 QPS。

### RateLimiter

创建

```JAVA
/**
* 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
* 当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
* 当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个请求
*/
public static RateLimiter create(double permitsPerSecond);

/**
* 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
* 还包含一个热身期(warmup period),热身期内，RateLimiter会平滑的将其释放令牌的速率加大，直到起达到最大速率
* 同样，如果RateLimiter在热身期没有足够的请求(unused),则起速率会逐渐降低到冷却状态
* 
* 设计这个的意图是为了满足那种资源提供方需要热身时间，而不是每次访问都能提供稳定速率的服务的情况(比如带缓存服务，需要定期刷新缓存的)
* 参数warmupPeriod和unit决定了其从冷却状态到达最大速率的时间
*/
public static RateLimiter create(double permitsPerSecond, long warmupPeriod, TimeUnit unit);

```

两个获取令牌的方法

```java
public double acquire();

public double acquire(int permits);
```

尝试获取令牌

```JAVA
public boolean tryAcquire();
//尝试获取一个令牌,立即返回
public boolean tryAcquire(int permits);
public boolean tryAcquire(long timeout, TimeUnit unit);
//尝试获取permits个令牌,带超时时间
public boolean tryAcquire(int permits, long timeout, TimeUnit unit);
```



# 参考

* [RateLimiter](http://xiaobaoqiu.github.io/blog/2015/07/02/ratelimiter/)
* [RateLimiter解析(一) ——设计哲学与快速使用](https://tech.kujiale.com/ratelimiter-architecture/)
