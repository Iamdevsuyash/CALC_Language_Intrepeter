package evaluator;

import java.util.List;

public class Evaluator {
    List<Instruction> parsedlist;
    Environment env;

    public Evaluator(List<Instruction> parsedlist){
        this.parsedlist = parsedlist;
        this.env = new Environment();
    }

    public void evaluate(){
        for (Instruction instruction : parsedlist) {
            instruction.execute(env);
        }
    }
}
