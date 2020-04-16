## Usage

```shell script
curl -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"multiplier","params":{"a":5,"b":6}}' http://localhost:8080/calculator
```
get

```json
{
    "jsonrpc": "2.0",
    "id": "1",
    "result": 30
}
```

## Reference

* [JSON RPC for Java Client & Spring Boot (Server example)](https://github.com/briandilley/jsonrpc4j/wiki/JSON-RPC-for-Java-Client-&-Spring-Boot-(Server-example))
