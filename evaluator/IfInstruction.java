package evaluator;
import parser.Expression;
import java.util.List;


public class IfInstruction implements Instruction{
    // Store: the condition expression and a List<Instruction> for the body.
    Expression condition;
    List<Instruction> ifbody;
    List<Instruction> elsebody;

    IfInstruction(Expression condition,List<Instruction> body,List<Instruction> elsebody){
        this.condition = condition;
        this.body = body;
        this.elsebody = elsebody;
    }

    @Override
    public void execute(Environment env) {
        // Evaluate the condition.
        boolean check = (boolean) condition.evaluate(env);

        // If the result is true, execute each instruction in the body.
        if (check == true){
            for (Instruction i:body){
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
