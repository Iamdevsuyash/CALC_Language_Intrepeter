package parsar;

import evaluator.Environment;

public class NumberNode implements Expression{
    // Store the numeric value as a double.
    // Set it in the constructor. No setters.
    Double num;

    public NumberNode(double num){
        this.num = num;
    }

    @Override
    public Object evaluate(Environment env) {
    // Return the stored number.
        return num;
    }
}
