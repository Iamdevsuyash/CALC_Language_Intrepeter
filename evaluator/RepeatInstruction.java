package evaluator;
import java.util.List;

public class RepeatInstruction implements Instruction {
    // Store: the repeat count and a List<Instruction> for the body.
    Integer count;
    List<Instruction> body;

    RepeatInstruction(Integer count,List<Instruction> b){
        this.count = count;
        this.body = b;
    }
    @Override
    public void execute(Environment env) {
        // Execute all body instructions, repeated count times.
        for (int i = 0; i< this.count;i++){
            for (Instruction ins:this.body){
                ins.execute(env);
            }
        }
    }
}