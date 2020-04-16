package org.apframework.jsonrpc4j.client.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;

public interface ExampleClientAPI {
    int multiplier(@JsonRpcParam(value = "a") int a, @JsonRpcParam(value = "b") int b);
}
