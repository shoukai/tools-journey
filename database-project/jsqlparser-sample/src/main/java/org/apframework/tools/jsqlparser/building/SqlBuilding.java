package org.apframework.tools.jsqlparser.building;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;

/**
 * https://github.com/JSQLParser/JSqlParser/wiki/Examples-of-SQL-building
 */
public class SqlBuilding {
    public static void main(String[] args) throws JSQLParserException {

        // 1 Building a simple select
        simpleSelect();

        // 2 Extending a simple insert
        simpleInsert();

        // 3 Replacing String values
        replacingStringValues();

    }

    private static void simpleSelect() throws JSQLParserException {
        Select simpleSelect = SelectUtils.buildSelectFromTable(new Table("mytable"));
        // select contains now select * from mytable.
        System.out.println(simpleSelect);
        // SELECT * FROM mytable

        Select columnSelect = SelectUtils.buildSelectFromTableAndExpressions(new Table("mytable"), new Column("a"), new Column("b"));
        // select contains now select a, b from mytable.
        System.out.println(columnSelect);
        // SELECT a, b FROM mytable

        Select expressionSelect = SelectUtils.buildSelectFromTableAndExpressions(new Table("mytable"), "a+b", "test");
        // Or even simpler, if you do not want to build the right expression tree you can provide simple textual expressions, that will be parsed and included in your select.
        System.out.println(expressionSelect);
        // SELECT a + b, test FROM mytable

        // RESULT :
        // SELECT * FROM mytable
        // SELECT a, b FROM mytable
        // SELECT a + b, test FROM mytable
        System.out.println("-----");
    }

    private static void simpleInsert() throws JSQLParserException {

        Insert insert = (Insert) CCJSqlParserUtil.parse("insert into mytable (col1) values (1)");
        System.out.println(insert.toString());
        // RESULT :  INSERT INTO mytable (col1) VALUES (1)

        //adding a column
        insert.getColumns().add(new Column("col2"));

        //adding a value using a visitor
        insert.getItemsList().accept(new ItemsListVisitor() {
            public void visit(SubSelect subSelect) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void visit(ExpressionList expressionList) {
                expressionList.getExpressions().add(new LongValue(5));
            }

            @Override
            public void visit(NamedExpressionList namedExpressionList) {

            }

            public void visit(MultiExpressionList multiExprList) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        System.out.println(insert.toString());
        // RESULT : INSERT INTO mytable (col1, col2) VALUES (1, 5)

        //adding another column
        insert.getColumns().add(new Column("col3"));
        //adding another value (the easy way)
        ((ExpressionList) insert.getItemsList()).getExpressions().add(new LongValue(10));
        System.out.println(insert.toString());
        // RESULT : INSERT INTO mytable (col1, col2, col3) VALUES (1, 5, 10)
        System.out.println("-----");
    }

    /**
     * Somebody wanted to publish some SQLs but wanted to scramble all concrete values. So here is a little example of how to achieve this.
     * In short a visitor scans through the complete tree, finds all StringValues and replaces the current value with XXXX.
     */
    private static void replacingStringValues() throws JSQLParserException {
        String sql = "SELECT NAME, ADDRESS, COL1 FROM USER WHERE SSN IN ('11111111111111', '22222222222222');";
        Select select = (Select) CCJSqlParserUtil.parse(sql);

        //Start of value modification
        StringBuilder buffer = new StringBuilder();
        ExpressionDeParser expressionDeParser = new ExpressionDeParser() {

            @Override
            public void visit(StringValue stringValue) {
                this.getBuffer().append("XXXX");
            }

        };
        SelectDeParser deparser = new SelectDeParser(expressionDeParser, buffer);
        expressionDeParser.setSelectVisitor(deparser);
        expressionDeParser.setBuffer(buffer);
        select.getSelectBody().accept(deparser);
        //End of value modification

        System.out.println(buffer.toString());
        //Result is: SELECT NAME, ADDRESS, COL1 FROM USER WHERE SSN IN (XXXX, XXXX)
        System.out.println("-----");
    }
}
