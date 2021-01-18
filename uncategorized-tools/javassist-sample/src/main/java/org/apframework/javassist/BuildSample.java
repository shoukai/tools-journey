package org.apframework.javassist;

import javassist.*;

/**
 * @Author: Shoukai Huang
 * @Date: 2019/6/18 11:42
 */
public class BuildSample {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault(); // 类池
        CtClass clazz = pool.makeClass("com.gs.Emp");

        //创建属性
        CtField f1 = CtField.make("private int num;", clazz);
        CtField f2 = CtField.make("private String name;", clazz);
        clazz.addField(f1);
        clazz.addField(f2);

        //创建方法
        CtMethod setName = CtMethod.make("public void setName(String name){this.name = name;}", clazz);
        CtMethod getName = CtMethod.make("public String getName(){return name;}", clazz);
        clazz.addMethod(setName);
        clazz.addMethod(getName);

        //添加构造器。  如果是带参构造器，需要传递参数类型，基本数据类型用CtClass获取，引用类型，需要用pool获取
        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, clazz);
        constructor.setBody("{this.num = num;this.name = name;}"); //构造器的方法体

        clazz.writeFile("./javassist-sample/");

        System.out.println("生成类成功！");
    }
}