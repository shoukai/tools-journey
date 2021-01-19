package org.apframework.tools.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.AddAliasesVisitor;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.List;

/**
 * https://github.com/JSQLParser/JSqlParser/wiki/Examples-of-SQL-parsing
 */
public class SqlParsing {

    public static void main(String[] args) throws JSQLParserException {
        // 1 Simple SQL parsing
        Statement simpleStatement = CCJSqlParserUtil.parse("SELECT * FROM tab1");
        System.out.println(simpleStatement.toString());
        // SELECT * FROM tab1
        System.out.println("-----");

        // 2 SQL script parsing
        Statements doubleStatements = CCJSqlParserUtil.parseStatements("SELECT * FROM tab1; SELECT * FROM tab2");
        System.out.println(doubleStatements.toString());
        // SELECT * FROM tab1;
        // SELECT * FROM tab2;
        System.out.println("-----");

        // 3 Simple Expression parsing
        Expression expr = CCJSqlParserUtil.parseExpression("a*(5+mycolumn)");
        System.out.println(expr);
        // a * (5 + mycolumn)
        System.out.println("-----");

        // 4 Extract table names from SQL
        Statement tableNameStatement = CCJSqlParserUtil.parse("SELECT * FROM MY_TABLE1");
        Select selectStatement = (Select) tableNameStatement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
        // In tableList are all table names of the parsed SQL statement.
        // The table names finder is an example of JSqlParser visitor pattern structure.
        // You can use the visitor pattern to traverse the parsing result and compute on it.
        System.out.println(tableList);
        // [MY_TABLE1]
        System.out.println("-----");

        // 5 Apply aliases to all expressions
        Select aliasesSelect = (Select) CCJSqlParserUtil.parse("select a,b,c from test");
        final AddAliasesVisitor instance = new AddAliasesVisitor();
        aliasesSelect.getSelectBody().accept(instance);
        // As a result you will get SELECT a AS A1, b AS A2, c AS A3 FROM test. At the moment the prefix can be configured.
        System.out.println(aliasesSelect);
        // SELECT a AS A1, b AS A2, c AS A3 FROM test
        System.out.println("-----");

        // 6 Add a column or expression to a select
        Select addColumnSelect = (Select) CCJSqlParserUtil.parse("select a from mytable");
        SelectUtils.addExpression(addColumnSelect, new Column("b"));
        // Now select contains SELECT a, b FROM mytable.
        System.out.println(addColumnSelect);
        // SELECT a, b FROM mytable
        System.out.println("-----");
    }
}
