package org.apframework.jsonrpc4j.server.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.apframework.jsonrpc4j.server.api.ExampleServerAPI;
import org.springframework.stereotype.Service;

@Service
@AutoJsonRpcServiceImpl
public class ExampleServerAPIImpl implements ExampleServerAPI {
    @Override
    public int multiplier(int a, int b) {
        return a * b;
    }
}