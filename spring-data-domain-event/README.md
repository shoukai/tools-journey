## 参考

https://github.com/wiselyman/spring-data-domain-event

## 改动

 pg to h2 db
 
## 运行

```SHELL
curl --location --request POST 'http://localhost:8080/people' \
--header 'Content-Type: application/json' \
--header 'Cookie: SESSION=bb27c667-9d1d-4538-ad9c-0600abe5700f' \
--data-raw '{
    "name":"hello",
    "gender":1
}'
```
