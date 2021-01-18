# vert.x

### 介绍

优势：

* 性能，它的底层是netty，并且编程模型跟node.js如出一辙，可算得上是“node on JVM”。同时，性能评测上比node还高出不少。
* 简单，它比netty更简单，而且可以轻易的支持cluster。
* Actor模型，Verticle + Eventbus，降低了并发编程的难度。
* WebSocket，恰好当时的项目需要这样的方案，服务器主动向前台推。并且Vert.x提供的EventbusBridge让前端js的组织更好。
* 支持Groovy，用过的都知道，这里就不展开了。
* 轻量级，部署简单。

资源：

* [Vert.x入坑须知（1）](https://segmentfault.com/a/1190000008441871)
* [Vert.x入坑须知（2）](https://segmentfault.com/a/1190000009642881)
* [Vert.x入坑须知（3）](https://segmentfault.com/a/1190000012359667)
* [vertx 例子](https://github.com/vert-x3/vertx-examples)
* [Vert.x 官方文档中文翻译](https://vertxchina.github.io/vertx-translation-chinese/)
* [Vert.x 蓝图 - 待办事项服务开发教程](http://www.sczyh30.com/vertx-blueprint-todo-backend/cn/)


