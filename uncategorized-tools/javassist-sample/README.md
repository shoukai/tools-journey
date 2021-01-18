# Javassist

## 功能介绍

运行时操作字节码可以让我们实现如下功能：

* 动态生成新的类
* 动态改变某个类的结构（添加/删除/修改 新的属性/方法）

## 优缺点

使用Javassist需要使用javassist.jar

优势：
* 比反射开销小，性能高。
* Javasist性能高于反射，低于ASM

局限性：

* JDK新语法不支持（包括泛型、枚举），不支持注解修改，但可以通过底层的javasist类来解决，具体参考：javassist.bytecode.annotaion
* 不支持数组的初始化，如 String[]{"1","2"}，除非只有数组的容量为1
* 不支持内部类和匿名类
* 不支持 continue 和 break 表达式。
* 对于继承关系，有些不支持 。例如：- class A{} - class B extends A{} - class C enxends B {}

应用场景：
* AOP：给一个类增加新的方法；给一段语句前面和后面（before/after/around）动态的加代码
* Reflection:起到类似反射的效果

## 参考资源

* [Java知识点总结（动态字节码操作-Javassist介绍）](https://segmentfault.com/a/1190000015654823)
* [Java知识点总结（动态字节码操作-Javassist的API使用）](https://segmentfault.com/a/1190000015657952)