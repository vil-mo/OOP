package ru.nsu.berezin.expressions;

/**
 * Expression that represents a variable.
 * Variable is any string of characters in english alphabet.
 */
public class Variable extends Expression {
    private final String name;

    /**
     * Creates a new variable expression.
     * 
     * @param name Name of the variable
     */
    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String print() {
        return name;
    }

    @Override
    public Expression derivative(String variable) {
        if (variable.equals(name)) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public double eval(String denoting) {
        String[] split = denoting.split(";");
        for (String s : split) {
            String[] split2 = s.split("=");
            if (split2.length != 2) {
                throw new IllegalArgumentException("Incorrect denoting format. Expected format: x = 1; y = 2");
            }

            String varName = split2[0].strip();

            if (varName.equals(name)) {
                try {
                    return Double.parseDouble(split2[1].strip());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Incorrect denoting format. Expected format: x = 1; y = 2");
                }
            }
        }
        throw new IllegalArgumentException("Variable " + name + " not found");
    }
}