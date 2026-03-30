package parser;

public class NumberNode implements Expression {
    private double value;

    public NumberNode(double value) {
        this.value = value;
    }
    public Object evaluate() {
        return value;
    }
}