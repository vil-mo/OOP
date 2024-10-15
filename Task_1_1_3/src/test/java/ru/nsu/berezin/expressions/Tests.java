package ru.nsu.berezin.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import org.junit.jupiter.api.Test;

class Tests {

    @Test
    public void parseAndPrint() {
        String stringExpression = "(x + y)";
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals(stringExpression, expr.print());
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    public void parseAndPrint2() {
        String stringExpression = "((x + y) + z)";
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals(stringExpression, expr.print());
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    public void parseAndPrint3() {
        String stringExpression = "(0.1 + (4.0 * 1.0))";
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals(stringExpression, expr.print());
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    void parseAndPrint4() {
        String stringExpression = "((x / 4.0) + ((y * z) - (5.3 * 1.1)))";
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals(stringExpression, expr.print());
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    public void parseAndPrint5() {
        String stringExpression = "1.";
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals("1.0", expr.print());
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    public void parseIncorrect() {
        String stringExpression = "+(x + y) / 3";
        try {
            Expression.parse(stringExpression);
            fail("Parsed invalid expression");
        } catch (ParseException ignored) {
        }
    }

    @Test
    public void parseIncorrect2() {
        String stringExpression = "(x + y";
        try {
            Expression.parse(stringExpression);
            fail("Parsed invalid expression");
        } catch (ParseException ignored) {
        }
    }

    @Test
    public void parseIncorrect3() {
        String stringExpression = "(x + y + z)";
        try {
            Expression.parse(stringExpression);
            fail("Parsed invalid expression");
        } catch (ParseException ignored) {
        }
    }

    @Test
    public void testIncorrect4() {
        String stringExpression = "%a";
        try {
            Expression.parse(stringExpression);
            fail("Parsed invalid expression");
        } catch (ParseException ignored) {
        }
    }

    @Test
    public void testIncorrect5() {
        String stringExpression = ".4";
        try {
            Expression.parse(stringExpression);
            fail("Parsed invalid expression");
        } catch (ParseException ignored) {
        }
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
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals(2.0, expr.eval("x = 3; y = 1;z=   8"));
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    public void testDerivative() {
        String stringExpression = "(x + 6)";
        try {
            Expression expr = Expression.parse(stringExpression);
            Expression derivative = expr.derivative("x");
            assertEquals(1, derivative.eval("x=10"));
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    public void testDerivative2() {
        String stringExpression = "(x * 3)";
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals(3, expr.derivative("x").eval("x=10"));
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    public void testDerivative3() {
        String stringExpression = "(x / 3)";
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals(1.0 / 3.0, expr.derivative("x").eval("x=10"));
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }

    @Test
    void testDerivative4() {
        String stringExpression = "((((8 * x) * x) - 3) / (y * 4))";
        try {
            Expression expr = Expression.parse(stringExpression);
            assertEquals(10, expr.derivative("x").eval("x=10; y = 4"));
        } catch (ParseException e) {
            fail("Failed to parse valid expression");
        }
    }
}
