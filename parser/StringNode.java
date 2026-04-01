package parser;
import evaluator.Environment;

// Represents a String literal
public class StringNode implements Expression {
    private String value; // Stores the string value

    public StringNode(String value) { // initializes the string
        this.value = value;
    }
    @Override
    public Object evaluate(Environment env) { 
        return value; // returns the string value when evaluated
    }
}