package org.apframework.jexl.sample;


import org.apache.commons.jexl3.*;

/**
 * @Author: Shoukai Huang
 * @Date: 2018/11/5 16:46
 */
public class JexlSample {

    private static final JexlEngine jexl = new JexlBuilder().cache(512).strict(true).silent(false).create();

    static String calculateTax = "G1 =~ [101,102] and G2 =~ [201,203] and G3 =~ [1001,1003,1004]";
    static JexlExpression e = jexl.createExpression(calculateTax);

    public static void main(String[] args) {
        testSimple();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            testComplex();
        }
        System.out.println("use : " + (System.currentTimeMillis() - begin));
    }

    public static void testComplex() {
        JexlContext context = new MapContext();
        context.set("G1", 101);
        context.set("G2", 201);
        context.set("G3", 1001);
//        System.out.println(e.evaluate(context));

        context.set("G1", 101);
        context.set("G2", 201);
        context.set("G3", 1004);
//        System.out.println(e.evaluate(context));

        context.set("G1", 102);
        context.set("G2", 202);
        context.set("G3", 1001);
//        System.out.println(e.evaluate(context));
    }

    public static void testSimple() {
        String calculateTax = "((G1 + G2 + G3) * 0.1) + G4";
        JexlExpression e = jexl.createExpression(calculateTax);

        JexlContext context = new MapContext();
        context.set("G1", 10);
        context.set("G2", 20);
        context.set("G3", 30);
        context.set("G4", 5);

        Number result = (Number) e.evaluate(context);
        System.out.println(result);
    }
}
