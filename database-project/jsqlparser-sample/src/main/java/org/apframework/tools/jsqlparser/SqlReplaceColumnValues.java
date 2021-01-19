package org.apframework.tools.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;
import net.sf.jsqlparser.util.deparser.StatementDeParser;

/**
 * https://github.com/JSQLParser/JSqlParser/wiki/Examples-of-SQL-building#replacing-string-values
 */
public class SqlReplaceColumnValues {

    static class ReplaceColumnAndLongValues extends ExpressionDeParser {

        @Override
        public void visit(StringValue stringValue) {
            this.getBuffer().append("?");
        }

        @Override
        public void visit(LongValue longValue) {
            this.getBuffer().append("?");
        }
    }

    public static String cleanStatement(String sql) throws JSQLParserException {
        StringBuilder buffer = new StringBuilder();
        ExpressionDeParser expr = new ReplaceColumnAndLongValues();

        SelectDeParser selectDeparser = new SelectDeParser(expr, buffer);
        expr.setSelectVisitor(selectDeparser);
        expr.setBuffer(buffer);
        StatementDeParser stmtDeparser = new StatementDeParser(expr, selectDeparser, buffer);

        Statement stmt = CCJSqlParserUtil.parse(sql);

        stmt.accept(stmtDeparser);
        return stmtDeparser.getBuffer().toString();
    }

    public static void main(String[] args) throws JSQLParserException {
        System.out.println(cleanStatement("SELECT 'abc', 5 FROM mytable WHERE col='test'"));
        System.out.println(cleanStatement("UPDATE table1 A SET A.columna = 'XXX' WHERE A.cod_table = 'YYY'"));
        System.out.println(cleanStatement("INSERT INTO example (num, name, address, tel) VALUES (1, 'name', 'test ', '1234-1234')"));
        System.out.println(cleanStatement("DELETE FROM table1 where col=5 and col2=4"));

        /*
         * SELECT ?, ? FROM mytable WHERE col = ?
         * UPDATE table1 A SET A.columna = ? WHERE A.cod_table = ?
         * INSERT INTO example (num, name, address, tel) VALUES (?, ?, ?, ?)
         * DELETE FROM table1 WHERE col = ? AND col2 = ?
         */
    }
}
