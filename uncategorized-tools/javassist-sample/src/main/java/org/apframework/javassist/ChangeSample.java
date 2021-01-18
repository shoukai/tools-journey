package org.apframework.javassist;

import javassist.*;
import org.apframework.javassist.bean.Author;
import org.apframework.javassist.bean.Emp;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: Shoukai Huang
 * @Date: 2019/6/18 11:47
 */
public class ChangeSample {

    public static void main(String[] args) throws InterruptedException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = null;
        try {
            cc = pool.get("org.apframework.javassist.bean.Emp");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        test1(pool, cc);
        test2(pool, cc);
//        test3(pool, cc);
//        test5(pool, cc);
//        test6(pool, cc);
        Thread.sleep(10 * 60 * 1000);
    }

    /**
     * 通过类名加载已有的类
     */
    public static void test1(ClassPool pool, CtClass cc) {
        try {
            cc = pool.get("org.apframework.javassist.bean.Emp");
            // 转化为字节码
            byte[] bytes = cc.toBytecode();
            System.out.println(Arrays.toString(bytes));
            // 获取类名
            System.out.println("getName: " + cc.getName());
            System.out.println("getSimpleName: " + cc.getSimpleName());
            // 获取父类
            System.out.println("getSuperclass: " + cc.getSuperclass().getName());
            for (CtClass item : cc.getInterfaces()) {
                System.out.println("getInterfaces name: " + item.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 动态生成一个方法，并调用
    public static void test2(ClassPool pool, CtClass cc) {

        try {
            // 当CtClass对象通过writeFile()、toClass()、toBytecode()转化为Class后，Javassist冻结了CtClass对象，
            // 因此，JVM不允许再次加载Class文件，所以不允许对其修改。因此，若想对CtClass对象进行修改，必须对其进行解冻，通过defrost()方法进行*/
            cc.defrost();
            // 生成一个方法
            CtMethod add = CtNewMethod.make("public int add(int a,int b){return $1+$2;}", cc);
            // 生成一个方法
            CtMethod subtraction = new CtMethod(CtClass.intType, "subtraction",
                    new CtClass[]{CtClass.intType, CtClass.intType}, cc);
            subtraction.setModifiers(Modifier.PUBLIC);
            subtraction.setBody("return $1-$2;");

            cc.addMethod(add);
            cc.addMethod(subtraction);

            // 通过反射调用生成的方法
            Class clazz = cc.toClass();
            Emp emp = (Emp) clazz.newInstance();
            // Method method = clazz.getDeclaredMethod("add", new
            // Class[]{int.class,int.class});
            Method method = clazz.getDeclaredMethod("subtraction", new Class[]{int.class, int.class});
            Object result = method.invoke(emp, 200, 300);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 方法体前面和后面加执行语句
    public static void test3(ClassPool pool, CtClass cc) {

        try {
            cc.defrost();
            CtMethod m1 = cc.getDeclaredMethod("sayHello", new CtClass[]{pool.get("java.lang.String")});
            m1.insertBefore("System.out.println(\"方法体前面\");");
            m1.insertAfter("System.out.println($1);");

            // 通过反射调用生成的方法
            Class clazz = cc.toClass();
            Emp emp = (Emp) clazz.newInstance();
            Method method = clazz.getDeclaredMethod("sayHello", new Class[]{String.class});
            method.invoke(emp, "张三");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 生成属性和方法
    public static void test4(ClassPool pool, CtClass cc) {
        try {
            // CtField.make("private double salary;", cc);
            CtField field = new CtField(CtClass.doubleType, "salary", cc);
            field.setModifiers(Modifier.PRIVATE);
            cc.addField(field);

            // cc.getDeclaredField("salary"); //获取属性

            CtMethod method = CtNewMethod.getter("getSalary", field);
            cc.addMethod(method);

            CtMethod method2 = CtNewMethod.getter("setSalary", field);
            cc.addMethod(method2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 生成构造器
    public static void test5(ClassPool pool, CtClass cc) {

        try {
            cc.defrost();
            CtConstructor[] cs = cc.getConstructors();
            for (CtConstructor c : cs) {
                System.out.println(c.getLongName());
                c.insertAfter("System.out.println(\"可以在构造器前后加代码\");");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 注解操作
    public static void test6(ClassPool pool, CtClass cc) {
        try {
            cc.defrost();
            Object[] annotations = cc.getAnnotations();
            Author author = (Author) annotations[0];
            System.out.println("name:" + author.name() + ",year:" + author.year());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
