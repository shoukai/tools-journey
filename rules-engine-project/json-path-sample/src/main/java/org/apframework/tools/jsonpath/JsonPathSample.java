package org.apframework.tools.jsonpath;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author apframework
 * <p>
 * document https://github.com/json-path/JsonPath
 */
public class JsonPathSample {

    public static void main(String[] args) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("path-examples.json");
        if (inputStream == null) {
            System.out.println("file of path-examples.json not exist");
            return;
        }
        String json = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        readingDocument(json);
    }

    private static void readingDocument(String json) {
        // read once
        List<String> authors = JsonPath.read(json, "$.store.book[*].author");
        // ["Nigel Rees","Evelyn Waugh","Herman Melville","J. R. R. Tolkien"]
        System.out.println(authors);

        // the document will be parsed every time you call
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        String author0 = JsonPath.read(document, "$.store.book[0].author");
        String author1 = JsonPath.read(document, "$.store.book[1].author");

        // Nigel Rees
        System.out.println(author0);
        // Evelyn Waugh
        System.out.println(author1);
    }


}
