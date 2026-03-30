package parser;

public class BinaryOpNode implements Expression {
    private Expression left;
    private String operator;
    private Expression right;

    public BinaryOpNode(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Object evaluate() {
        Object leftval = left.evaluate();
        Object rightval = right.evaluate();

        if (operator.equals("+")) {
            try{
                return ((Double) leftval) + ((Double) rightval);
            }catch(ClassCastException e){
                return String.valueOf(leftval) + String.valueOf(rightval);
            }
        } else if (operator.equals("-")) {
            return ((Double) leftval) - ((Double) rightval);
        } else if (operator.equals("*")) {
            return ((Double) leftval) * ((Double) rightval);
        } else if (operator.equals("/")) {
            double rightVal = (Double) rightval;
            if(rightVal == 0.0){
                throw new ArithmeticException("Can't divide by zero");
            }
            return ((Double) leftval) / rightVal;
        }
        else if (operator.equals(">")) {
            return ((Double) leftval) > ((Double) rightval);
        } else if (operator.equals("<")) {
            return ((Double) leftval) < ((Double) rightval  );
        } else if (operator.equals("==")) {
            return leftval.equals(rightval);
        }
        else{
            throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}