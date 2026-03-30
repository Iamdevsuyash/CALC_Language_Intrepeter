package test;
import java.util.List;

import tokenizer.Token;
import tokenizer.Tokenizer;

public class TestTokenizer {
    public static void main(String[] args) {

        String input = """
            result := 10
            >> "Hello"
            x := 5 + 3 * 2
            ? x > 5
            @ 3
        """;

        try {
            Tokenizer tokenizer = new Tokenizer(input);
            List<Token> tokens = tokenizer.tokenize();

            for (Token token : tokens) {
                System.out.println(token);
            }

        } catch (RuntimeException e) {
            System.out.println("Tokenization Error:");
            System.out.println(e.getMessage());
        }
    }
}