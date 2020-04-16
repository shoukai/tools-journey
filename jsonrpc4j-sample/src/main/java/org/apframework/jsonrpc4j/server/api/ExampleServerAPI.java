package org.apframework.jsonrpc4j.server.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/calculator")
public interface ExampleServerAPI {
    int multiplier(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b);
}
