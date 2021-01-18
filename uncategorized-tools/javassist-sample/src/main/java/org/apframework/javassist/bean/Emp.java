package org.apframework.javassist.bean;

/**
 * @Author: Shoukai Huang
 * @Date: 2019/6/18 14:07
 */
@Author(name = "gs",year=2015)
public class Emp {
    private int num;
    private String name;

    public Emp() {
    }

    public Emp(int num, String name) {
        this();
        this.num = num;
        this.name = name;
    }

    public void sayHello(String name){
        System.out.print("你好,");
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}