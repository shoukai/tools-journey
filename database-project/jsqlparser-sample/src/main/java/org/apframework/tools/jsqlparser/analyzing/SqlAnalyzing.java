package org.apframework.tools.jsqlparser.analyzing;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.*;

import java.util.HashMap;
import java.util.Map;

/**
 * https://github.com/JSQLParser/JSqlParser/wiki/Examples-of-SQL-analyzing
 */
public class SqlAnalyzing {

    public static void main(String[] args) throws JSQLParserException {

        // The task here is to build a map, where the alias is the key and the expression to this alias the value of one Map entry.
        Select stmt = (Select) CCJSqlParserUtil.parse("SELECT col1 AS a, col2 AS b, col3 AS c FROM table WHERE col1 = 10 AND col2 = 20 AND col3 = 30");

        Map<String, Expression> map = new HashMap<>();
        for (SelectItem selectItem : ((PlainSelect) stmt.getSelectBody()).getSelectItems()) {
            selectItem.accept(new SelectItemVisitorAdapter() {
                @Override
                public void visit(SelectExpressionItem item) {
                    map.put(item.getAlias().getName(), item.getExpression());
                }
            });
        }

        System.out.println("map " + map);
    }
}
