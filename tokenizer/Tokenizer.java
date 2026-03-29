package tokenizer;

import java.util.*;


// Tokenizer class that takes a string and produces a list of tokens
public class Tokenizer {
    private final String s;
    private int i = 0, line = 1, col = 1;
    private final List<Token> tokens = new ArrayList<>();


    //define the two character tokens and their corresponding types
    private static final Map<String, Token.Type> twoChar = Map.of(
        ":=", Token.Type.ASSIGN,
        "==", Token.Type.EQ,
        "=>", Token.Type.ARROW,
        ">>", Token.Type.PRINT
    );


    // define the one character tokens and their corresponding types
    private static final Map<Character, Token.Type> oneChar = Map.ofEntries(
        Map.entry('+', Token.Type.PLUS),
        Map.entry('-', Token.Type.MINUS),
        Map.entry('*', Token.Type.MULTIPLY),
        Map.entry('/', Token.Type.DIVIDE),
        Map.entry('(', Token.Type.LPAREN),
        Map.entry(')', Token.Type.RPAREN),
        Map.entry('<', Token.Type.LT),
        Map.entry('>', Token.Type.GT),
        Map.entry('?', Token.Type.IF),
        Map.entry('@', Token.Type.REPEAT),
        Map.entry(':', Token.Type.COLON)
    );

}