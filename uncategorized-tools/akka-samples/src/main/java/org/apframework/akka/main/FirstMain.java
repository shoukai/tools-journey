package org.apframework.akka.main;

/**
 * @Author: Shoukai Huang
 * @Date: 2019/7/4 18:57
 */
public class FirstMain {
    public static void main(String[] args) {
        akka.Main.main(new String[] { HelloWorld.class.getName() });
    }
}
