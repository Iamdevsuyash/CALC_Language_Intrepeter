package evaluator;
import parsar.Expression;

public class AssignInstruction implements Instruction {
    // Store: the variable name and the expression whose value will be assigned.
    private String name;
    private Expression expression;

    AssignInstruction(String name,Expression exp){
        this.name = name;
        this.expression= exp;
    }

    @Override
    public void execute(Environment env) {
        // Evaluate the expression, then store the result in the Environment.
        Object val = this.expression.evaluate(env);
        env.set(this.name,val);

    }
}
