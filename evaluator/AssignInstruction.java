package evaluator;
import parser.Expression;

public class AssignInstruction implements Instruction {
    // Store: the variable name and the expression whose value will be assigned.
    private final String name;
    private final Expression expression;

    public AssignInstruction(String name,Expression exp){
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
