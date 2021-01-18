# Hystrix

## Hystrix设计目标：

* 对来自依赖的延迟和故障进行防护和控制——这些依赖通常都是通过网络访问的
* 阻止故障的连锁反应
* 快速失败并迅速恢复
* 回退并优雅降级
* 提供近实时的监控与告警

## Hystrix遵循的设计原则：

* 防止任何单独的依赖耗尽资源（线程）
* 过载立即切断并快速失败，防止排队
* 尽可能提供回退以保护用户免受故障
* 使用隔离技术（例如隔板，泳道和断路器模式）来限制任何一个依赖的影响
* 通过近实时的指标，监控和告警，确保故障被及时发现
* 通过动态修改配置属性，确保故障及时恢复
* 防止整个依赖客户端执行失败，而不仅仅是网络通信

## Hystrix如何实现这些设计目标？

* 使用命令模式将所有对外部服务（或依赖关系）的调用包装在HystrixCommand或HystrixObservableCommand对象中，并将该对象放在单独的线程中执行；
* 每个依赖都维护着一个线程池（或信号量），线程池被耗尽则拒绝请求（而不是让请求排队）。
* 记录请求成功，失败，超时和线程拒绝。
* 服务错误百分比超过了阈值，熔断器开关自动打开，一段时间内停止对该服务的所有请求。
* 请求失败，被拒绝，超时或熔断时执行降级逻辑。
* 近实时地监控指标和配置的修改。

### 参考
* [Getting-Started](https://github.com/Netflix/Hystrix/wiki/Getting-Started)
* [How it Works](https://github.com/Netflix/Hystrix/wiki/How-it-Works)
* [聊聊Hystrix中的命令模式](https://www.jianshu.com/p/c8a998c9a571)
* [Hystrix使用详解](https://www.cnblogs.com/yepei/p/7169127.html)
* [Hystrix原理与实战](https://blog.csdn.net/loushuiyifan/article/details/82702522)
