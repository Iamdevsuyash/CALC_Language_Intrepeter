package parser;
import evaluator.Environment;

// Represents a numeric literal 
public class NumberNode implements Expression {
    private double value; // stores the numeric value

    public NumberNode(double value) { // initializes the number
        this.value = value;
    }
    @Override
    public Object evaluate(Environment env) { 
        return value; // returns as Double when evaluated
    }
}