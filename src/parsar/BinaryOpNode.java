package parsar;

import evaluator.Environment;

public class BinaryOpNode implements Expression {
    // Store: left expression, operator symbol (e.g. "+", "-", ">"), right expression.
    // Set all three in the constructor. No setters.
    @Override
    public Object evaluate(Environment env) {
        // 1. Evaluate the left expression.
        // 2. Evaluate the right expression.
        // 3. Apply the operator to the two results and return the answer.
        // Arithmetic operators return a Double.
        // Comparison operators (>, <, ==) return a Boolean.
    }
}
