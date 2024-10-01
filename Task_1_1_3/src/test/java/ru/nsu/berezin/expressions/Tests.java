package ru.nsu.berezin.expressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

public class Tests {

    @Test
    public void parseAndPrint() {
        String stringExpression = "(x + y)";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(stringExpression, expr.print());
    }

    @Test
    public void parseAndPrint2() {
        String stringExpression = "((x + y) + z)";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(stringExpression, expr.print());
    }

    @Test
    public void parseAndPrint3() {
        String stringExpression = "(0.1 + (4.0 * 1.0))";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(stringExpression, expr.print());
    }

    @Test
    void parseAndPrint4() {
        String stringExpression = "((x / 4.0) + ((y * z) - (5.3 * 1.1)))";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(stringExpression, expr.print());
    }

    @Test
    public void parseAndPrint5() {
        String stringExpression = "1.";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals("1.0", expr.print());
    }

    @Test
    public void parseIncorrect() {
        String stringExpression = "+(x + y) / 3";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(null, expr);
    }

    @Test
    public void parseIncorrect2() {
        String stringExpression = "(x + y";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(null, expr);
    }

    @Test
    public void parseIncorrect3() {
        String stringExpression = "(x + y + z)";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(null, expr);
    }

    @Test
    public void testIncorrect4() {
        String stringExpression = "%a";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(null, expr);
    }

    @Test
    public void testIncorrect5() {
        String stringExpression = ".4";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(null, expr);
    }

    @Test
    public void evalNumber() {
        Expression expr = new Number(1.0);
        assertEquals(1.0, expr.eval(""));
    }

    @Test
    public void evalVariable() {
        Expression expr = new Variable("xbc");
        assertEquals(6.0, expr.eval("xbc = 6"));
    }

    @Test
    public void evalAdd() {
        Expression expr = new Add(new Number(1.0), new Variable("xbc"));
        assertEquals(7.0, expr.eval("xbc = 6"));
    }

    @Test
    public void evalSub() {
        Expression expr = new Sub(new Number(1.0), new Variable("x"));
        assertEquals(-5.0, expr.eval("x = 6"));
    }

    @Test
    public void evalMul() {
        Expression expr = new Mul(new Number(4.0), new Variable("x"));
        assertEquals(24.0, expr.eval("x = 6"));
    }

    @Test
    public void evalDiv() {
        Expression expr = new Div(new Number(12.0), new Variable("x"));
        assertEquals(3, expr.eval("x = 4"));
    }

    @Test
    public void evalComplexExpression() {
        String stringExpression = "((x * 4.0) + ((y - z) - (6 * 0.5)))";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(2.0, expr.eval("x = 3; y = 1;z=   8"));
    }
}
