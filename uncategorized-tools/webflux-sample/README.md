# Reactive Programming with Spring 5 

### City Sample

createCity

```
curl -d '{"provinceId":1, "cityName":"shenyang", "description":"description"}' -H "Content-Type: application/json" -X POST http://localhost:8080/city
```

findAllCity

```
curl -H "Content-Type: application/json" -X GET http://localhost:8080/city
```

findOneCity

```
curl -H "Content-Type: application/json" -X GET http://localhost:8080/city/1
```

modifyCity
```
curl -d '{"id": 1, "provinceId":1, "cityName":"shenyang", "description":"..."}' -H "Content-Type: application/json" -X PUT http://localhost:8080/city
```

# SSE Sample

run

```
curl http://localhost:8080/sse/randomNumbers
```

output:
```
id:0
event:random
data:310921648

id:1
event:random
data:439723924

id:2
event:random
data:954906207
```

### WebSocket

运行应用之后，可以使用工具来测试该 WebSocket 服务。打开工具页面 https://www.websocket.org/echo.html，然后连接到 ws://localhost:8080/echo，可以发送消息并查看服务器端返回的结果。

### 参考资料

* [spring-boot](https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples)
* [springboot-learning-example](https://github.com/JeffLi1993)
* [使用 Spring 5 的 WebFlux 开发反应式 Web 应用](https://www.ibm.com/developerworks/cn/java/spring5-webflux-reactive/index.html)

