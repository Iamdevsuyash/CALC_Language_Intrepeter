package evaluator;

import parser.Expression;

public class PrintInstruction implements Instruction
{
    // Store: the expression to print.
    Expression exp;

    PrintInstruction(Expression exp){
        this.exp = exp;
    }
    @Override
    public void execute(Environment env) {
        // Evaluate the expression and print the result to standard output.
        Object output = exp.evaluate(env);
        System.out.println(output);
    }
}
