package test;

import java.util.List;

import tokenizer.Token;
import tokenizer.Tokenizer;
import evaluator.Evaluator;
import evaluator.Instruction;
import parser.Parser;

public class TestEvaluator {
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
            Evaluator evaluator = new Evaluator(parsed);
            evaluator.evaluate();

        } catch (RuntimeException e) {
            System.out.println("Tokenization Error:");
            System.out.println(e.getMessage());
        }
    }
}