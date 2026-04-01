package evaluator;

import java.util.List;

public class Evaluator {
    List<Instruction> parsedlist;
    Environment env;

    Evaluator(List<Instruction> parsedlist){
        this.parsedlist = parsedlist;
        this.env = new Environment();
    }

}
