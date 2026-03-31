package evaluator;
import parser.Expression;
import java.util.List;


public class IfInstruction implements Instruction{
    // Store: the condition expression and a List<Instruction> for the body.
    private final Expression condition;
    private final List<Instruction> ifbody;
    private final List<Instruction> elsebody;

    IfInstruction(Expression condition,List<Instruction> body,List<Instruction> elsebody){
        this.condition = condition;
        this.ifbody = body;
        this.elsebody = elsebody;
    }

    @Override
    public void execute(Environment env) {
        // Evaluate the condition.
        boolean check = (boolean) condition.evaluate(env);

        // If the result is true, execute each instruction in the body.
        if (check == true){
            for (Instruction i:ifbody){
                i.execute(env);
            }
        }
        else{
            for (Instruction i:elsebody){
                i.execute(env);
            }
        }


    }

}
