package test;

import java.util.List;

import tokenizer.Token;
import tokenizer.Tokenizer;
import evaluator.Evaluator;
import evaluator.Instruction;
import parser.Parser;

public class TestParser {
    public static void main(String[] args) {

        String input = """
                result := 10
                >> "Hello"
                x := 5 + 3 * 2
                @ 3 =>
                    >> x
                """;

        try {
            Tokenizer tokenizer = new Tokenizer(input);
            List<Token> tokens = tokenizer.tokenize();
            Parser parser = new Parser(tokens);
            List<Instruction> parsed = parser.parse();
            for(Instruction i: parsed){
                System.out.println(i);
            }
            
        } catch (RuntimeException e) {
            System.out.println("Parsing Error:");
            System.out.println(e.getMessage());
        }
    }
}