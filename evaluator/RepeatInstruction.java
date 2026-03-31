package evaluator;
import parser.Expression;
import java.util.List;

public class RepeatInstruction implements Instruction {
    // Store: the repeat count and a List<Instruction> for the body.
    private final Expression count;
    private final List<Instruction> body;

    RepeatInstruction(Expression count,List<Instruction> b){
        this.count = count;
        this.body = b;
    }
    @Override
    public void execute(Environment env) {
        int times = (int) count.evaluate(env);

        // Execute all body instructions, repeated count times.
        for (int i = 0; i< times;i++){
            for (Instruction ins:this.body){
                ins.execute(env);
            }
        }
    }
}