package org.apframework.jexl.sample;

import org.junit.Assert;

/**
 * Abstracts using a test within Junit or through a main method.
 */
public abstract class Output {
    /**
     * Creates an output using System.out.
     */
    private Output() {
        // nothing to do
    }

    /**
     * Outputs the actual and value or checks the actual equals the expected value.
     * @param expr the message to output
     * @param actual the actual value to output
     * @param expected the expected value
     */
    public abstract void print(String expr, Object actual, Object expected);

    /**
     * The output instance for Junit TestCase calling assertEquals.
     */
    public static final Output JUNIT = new Output() {
        @Override
        public void print(String expr, Object actual, Object expected) {
            System.out.println("expr:"+expr+",actual:"+actual+",expected:"+expected);
            Assert.assertEquals(expr, expected, actual);
        }
    };


    /**
     * The output instance for the general outputing to System.out.
     */
    public static final Output SYSTEM = new Output() {
        @Override
        public void print(String expr, Object actual, Object expected) {
            System.out.print(expr);
            System.out.println(actual);
        }
    };
}