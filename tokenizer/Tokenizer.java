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

    // constructor that takes a string and initializes the tokenizer
    public Tokenizer(String s) { this.s = s; }

    // method that produces a list of tokens from the input string
    public List<Token> tokenize() {

        while (i < s.length()) {
            char c = s.charAt(i);

            // whitespace
            if (c == ' ' || c == '\t' || c == '\r') { i++; col++; continue; }

            // newline
            if (c == '\n') {
                tokens.add(new Token(Token.Type.NEWLINE, "\n", null, line, col));
                i++; line++; col = 1;
                continue;
            }

            // string
            if (c == '"') {
                int start = ++i, startCol = col++;
                while (i < s.length() && s.charAt(i) != '"') {
                    if (s.charAt(i) == '\n')
                        throw new RuntimeException("Unterminated string at line " + line);
                    i++; col++;
                }
                if (i >= s.length())
                    throw new RuntimeException("Unterminated string at line " + line);

                String val = s.substring(start, i++);
                tokens.add(new Token(Token.Type.STRING, "\"" + val + "\"", val, line, startCol));
                col++;
                continue;
            }

            // number
            if (Character.isDigit(c)) {
                int start = i, startCol = col;
                while (i < s.length() && Character.isDigit(s.charAt(i))) { i++; col++; }

                if (i < s.length() && s.charAt(i) == '.' &&
                    i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    i++; col++;
                    while (i < s.length() && Character.isDigit(s.charAt(i))) { i++; col++; }
                }

                String val = s.substring(start, i);
                tokens.add(new Token(Token.Type.NUMBER, val, Double.parseDouble(val), line, startCol));
                continue;
            }

            // identifier
            if (Character.isLetter(c) || c == '_') {
                int start = i, startCol = col;
                while (i < s.length() &&
                      (Character.isLetterOrDigit(s.charAt(i)) || s.charAt(i) == '_')) {
                    i++; col++;
                }

                String val = s.substring(start, i);
                tokens.add(new Token(Token.Type.IDENTIFIER, val, val, line, startCol));
                continue;
            }

            // 2-char tokens
            if (i + 1 < s.length()) {
                String two = s.substring(i, i + 2);
                if (twoChar.containsKey(two)) {
                    tokens.add(new Token(twoChar.get(two), two, null, line, col));
                    i += 2; col += 2;
                    continue;
                }
            }

            // 1-char tokens
            if (oneChar.containsKey(c)) {
                tokens.add(new Token(oneChar.get(c), String.valueOf(c), null, line, col));
                i++; col++;
                continue;
            }

            throw new RuntimeException("Unexpected char: " + c + " at line " + line);
        }

        tokens.add(new Token(Token.Type.EOF, "", null, line, col));
        return tokens;
    }

}