package org.apframework.tools.antlr.java;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.apframework.tools.antlr.Java8BaseListener;
import org.apframework.tools.antlr.Java8Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.baeldung.com/java-antlr
 *
 * mvn package
 */
public class UppercaseMethodListener extends Java8BaseListener {

    private List<String> errors = new ArrayList<String>();

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        TerminalNode node = ctx.Identifier();
        String methodName = node.getText();

        if (Character.isUpperCase(methodName.charAt(0))) {
            errors.add(String.format("Method %s is uppercased!", methodName));
        }
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}