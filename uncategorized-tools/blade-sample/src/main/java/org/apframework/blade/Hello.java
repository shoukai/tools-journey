package org.apframework.blade;

import com.blade.Blade;

public class Hello {

    public static void main(String[] args) {
        Blade.me()
                .get("/hello", ((request, response) -> response.text("Hello World.")))
                .start(Hello.class, args);
    }
}