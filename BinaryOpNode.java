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
            return ((Double) leftval) + ((Double) rightval);
        } else if (operator.equals("-")) {
            return ((Double) leftval) - ((Double) rightval);
        } else if (operator.equals("*")) {
            return ((Double) leftval) * ((Double) rightval);
        } else if (operator.equals("/")) {
            return ((Double) leftval) / ((Double) rightval);
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