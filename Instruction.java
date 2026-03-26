import java.util.List;

public interface Instruction {
}

class AssignInstruction implements Instruction {
    public AssignInstruction(String name, Expression expr) {}
}

class PrintInstruction implements Instruction {
    public PrintInstruction(Expression expr) {}
}

class IfInstruction implements Instruction {
    public IfInstruction(Expression cond, List<Instruction> body) {}
}

class RepeatInstruction implements Instruction {
    public RepeatInstruction(Expression times, List<Instruction> body) {}
}