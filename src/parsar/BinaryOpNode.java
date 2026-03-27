package parsar;

import evaluator.Environment;

public class BinaryOpNode implements Expression {
    // Store: left expression, operator symbol (e.g. "+", "-", ">"), right expression.
    Expression left;
    Expression right;
    String operand;
    
    // Set all three in the constructor. No setters.
    public BinaryOpNode(String op, Expression l, Expression r){
        this.left = l;
        this.operand = op;
        this.right = r;
    }
    
    
    @Override
    public Object evaluate(Environment env) {
        // 1. Evaluate the left expression.
        Double l_val = (Double) left.evaluate(env);
        
        // 2. Evaluate the right expression.
        Double r_val = (Double) right.evaluate(env);
        
        Object ans;
        
        // 3. Apply the operator to the two results and return the answer.
        // Arithmetic operators return a Double.
        if (this.operand == "+") ans = l_val+ r_val;
        else if (this.operand == "-") ans = l_val-r_val;
        else if (this.operand == "*") ans = l_val*r_val;
        else if (this.operand == "/"){
            if (r_val == 0){
                throw new ArithmeticException("Cannot divide by zero");
            }
            ans = l_val / r_val;
        }

        // Comparison operators (>, <, ==) return a Boolean.

        else if (this.operand == ">") ans = l_val>r_val;
        else if(this.operand == "<") ans= l_val<r_val;
        else if(this.operand == "==") ans = l_val == r_val;
        else{
            throw new ArithmeticException("Unkown operand");
        }

        return ans;
    }
}
