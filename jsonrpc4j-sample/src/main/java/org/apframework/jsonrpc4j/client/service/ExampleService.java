package org.apframework.jsonrpc4j.client.service;

import org.apframework.jsonrpc4j.client.api.ExampleClientAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @Autowired
    private ExampleClientAPI exampleClientAPI;

    public int multiply(int a, int b) {
        return exampleClientAPI.multiplier(a, b);
    }
}