package org.apframework.unirest.sample;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class UnirestSample {

    // http://kong.github.io/unirest-java/

    public static void main(String[] args) {
        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .field("parameter", "value")
                .field("firstname", "Gary")
                .asJson();
        System.out.println(response.getBody());
        System.out.println(response.isSuccess());
        System.out.println(response.getBody().getObject().get("args"));

        // JsonNode getObject return JSONObject
        // PS : JSONObject IS NOT JSONObject in FastJson
    }
}
