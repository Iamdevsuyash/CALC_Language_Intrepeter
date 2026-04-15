import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import tokenizer.Token;
import tokenizer.Tokenizer;
import evaluator.Evaluator;
import evaluator.Instruction;
import parser.Parser;


public class Interpreter {

    public void run(String sourceCode){
        
    // Step 1: Pass sourceCode to a new Tokenizer and get the token
    Tokenizer tokenizer = new Tokenizer(sourceCode);
    List<Token> tokens = tokenizer.tokenize();
    
    // Step 2: Pass the token list to a new Parser and get the
    Parser parser = new Parser(tokens);
    List<Instruction> parsed = parser.parse();

    // Step 3: Create a new Environment, then execute each instruction.
    Evaluator evaluator = new Evaluator(parsed);
    evaluator.evaluate();
}

public static void main(String[] args) throws IOException{
    String path = args[0];
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
    StringBuilder s = new StringBuilder();
    int data;
    while ((data = br.read()) != -1) {
        // System.out.println(line);
        char c = (char) data;
        s.append(c);
    }
    
    Interpreter interpreter = new Interpreter();

    interpreter.run(s.toString());
}

}
}