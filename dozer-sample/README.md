
## 相关概念

Dozer是一个JavaBean映射工具库。

Dozer是Java Bean到Java Bean映射器，将数据从一个对象递归到另一个对象，它是一个开放源代码映射框架，是强大的，通用的，灵活的，可重用的和可配置的。

Dozer支持简单的属性映射，复杂类型映射，双向映射，隐式显式映射以及递归映射。 这包括在元素级别还需要映射的映射集合属性。

Dozer不仅支持属性名称之间的映射，而且还可以在类型之间自动转换。 大多数转换方案都是开箱即用的，但Dozer还允许您通过XML或基于代码的配置指定自定义转换。

## 使用方法

```XML
<dependency>
    <groupId>com.github.dozermapper</groupId>
    <artifactId>dozer-core</artifactId>
    <version>6.5.0</version>
</dependency>
```

## 注意事项

官网着重建议：在现实应用中，最好不要每次映射对象时都创建一个Mapper实例来工作，这样会产生不必要的开销。如果你不使用IoC容器（如：spring）来管理你的项目，那么，最好将Mapper定义为单例模式。


## Reference

* [DozerMapper](https://github.com/DozerMapper/dozer)
* [Dozer 介绍](https://www.dev996.com/dozer-introduce.html)
* [使用dozer实现对象转换](https://www.jianshu.com/p/dd059751931f)

