package parser;
import evaluator.Environment;

public class BinaryOpNode implements Expression {
    private Expression left; // left operand
    private String operator; // operator: +, -, *, /, >, <, ==
    private Expression right; // right operand

    public BinaryOpNode(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    @Override
    public Object evaluate(Environment env) { // evaluate binary expression
        // Recursively evaluate left and right sub-expressions
        Object leftval = left.evaluate(env);
        Object rightval = right.evaluate(env);

        // Perform operation based on operator
        if (operator.equals("+")) {
            try{
                // Try numeric addition first
                return ((Double) leftval) + ((Double) rightval);
            }catch(ClassCastException e){
                // If not numbers, do string concatenation
                return String.valueOf(leftval) + String.valueOf(rightval);
            }
        // For Subtraction
        } else if (operator.equals("-")) {
            try{
                return ((Double) leftval) - ((Double) rightval);
            }catch(ClassCastException e){
                throw new RuntimeException("Subtraction requires numbers"); // throws exception for non-numeric values subtraction
            }
            
        // For Multiplication
        } else if (operator.equals("*")) {
            return ((Double) leftval) * ((Double) rightval);
        // For Division
        } else if (operator.equals("/")) {
            double rightVal = (Double) rightval;
            if(rightVal == 0.0){
                throw new ArithmeticException("Can't divide by zero"); // throws exception if division by zero is attempted
            }
            return ((Double) leftval) / rightVal;
        }
        // For Greater Than
        else if (operator.equals(">")) {
            return ((Double) leftval) > ((Double) rightval);
        // For Less Than
        } else if (operator.equals("<")) {
            return ((Double) leftval) < ((Double) rightval  );
        // For Equality
        } else if (operator.equals("==")) {
            return leftval.equals(rightval);
        }
        // If operator is unknown, throw an exception
        else{
            throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}