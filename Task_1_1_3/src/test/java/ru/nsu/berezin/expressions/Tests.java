package ru.nsu.berezin.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testDerivative() {
        String stringExpression = "(x + 6)";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        Expression derivative = expr.derivative("x");
        assertEquals(1, derivative.eval("x=10"));
    }

    @Test
    public void testDerivative2() {
        String stringExpression = "(x * 3)";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(3, expr.derivative("x").eval("x=10"));
    }

    @Test
    public void testDerivative3() {
        String stringExpression = "(x / 3)";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        assertEquals(1.0 / 3.0, expr.derivative("x").eval("x=10"));
    }

    @Test
    void testDerivative4() {
        String stringExpression = "((((8 * x) * x) - 3) / (y * 4))";
        Expression expr = Expression.parse(new StringReader(stringExpression));
        System.out.println(expr.derivative("x").print());
        assertEquals(10, expr.derivative("x").eval("x=10; y = 4"));
    }
}
