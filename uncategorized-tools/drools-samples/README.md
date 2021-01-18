# drools-examples

about drools examples, spring boot drools examples

- [drools-simple-demo](https://github.com/ityouknow/drools-examples/tree/master/drools-simple-demo)：drools最简单的一个demo,方便新手理解入门

- [spring-boot-drools](https://github.com/ityouknow/drools-examples/tree/master/spring-boot-drools)：drools集成spring boot

> drools 在国内的资料偏少，而且有很多的坑，共享出来希望可以帮到和我一样的人，少走一些弯路。

## spring-boot-drools 测试

```
curl --location --request GET 'http://localhost:8080/test/address?num=5'
```

70985触发了2条规则

```
curl --location --request GET 'http://localhost:8080/test/address?num=6'
```

296089触发了1条规则

# 来源

* [ityouknow-github](https://github.com/ityouknow/drools-examples)
