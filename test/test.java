package test;

import evaluator.Environment;
import evaluator.Instruction;
import evaluator.PrintInstruction;
import parser.BinaryOpNode;
import parser.Expression;
import parser.NumberNode;
import parser.Parser;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.util.*;

public class test {
    public static void main(String[] args) {
        String[] tests = {
            "x := 10\ny := 3\nresult := x + y * 2\n>> result",
            "x := -10\ny := 3\nresult := x + y * 2\n>> result",
            "name := \"Sitare\"\n>> name\n>> \"Hello from CALC\"",
            "score := 85\n?score > 50 =>\n  >>\"Pass\"",
            "i := 1\n@ 4 =>\n   >> i\ni := i + 1\n"
        };

        for (String input : tests) {

            Tokenizer tokenizer = new Tokenizer(input);
            List<Token> tokens = tokenizer.tokenize();

            Parser parser = new Parser(tokens);
            List<Instruction> instructions = parser.parse();

            Environment env = new Environment();
            for (Instruction instr : instructions) {
                instr.execute(env);
            }
        }

    }
}
