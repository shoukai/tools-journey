package org.apframework.blade.example;

import com.blade.Blade;

public class Main {
    public static void main(String[] args) {
        Blade.me().get("/", (req, res) -> {
            res.text("Hello Blade");
        }).start();
    }
}
