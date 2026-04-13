package tokenizer;

import java.util.*;

public class Tokenizer {
    // Tokenization flow:
    // 1) At line start, measure leading whitespace and emit INDENT/DEDENT.
    // 2) Emit normal tokens (identifiers, numbers, operators, strings, NEWLINE).
    // 3) Before EOF, emit remaining DEDENT tokens to close all open blocks.
    private final String s;
    private int i = 0, line = 1, col = 1;
    private final List<Token> tokens = new ArrayList<>();
    private final Deque<Integer> indentLevels = new ArrayDeque<>();
    private boolean atLineStart = true;
    private static final int TAB_WIDTH = 4;

    private static final Map<String, Token.Type> twoChar = Map.of(
        ":=", Token.Type.ASSIGN,
        "==", Token.Type.EQ,
        "=>", Token.Type.ARROW,
        ">>", Token.Type.PRINT
    );

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

    public Tokenizer(String s) { this.s = s; }

    public List<Token> tokenize() {
        indentLevels.push(0);

        while (i < s.length()) {
            // Only leading whitespace at line start affects INDENT/DEDENT structure.
            if (atLineStart) {
                handleLineIndentation();
                if (atLineStart) {
                    continue;
                }
            }

            char c = s.charAt(i);

            // whitespace
            if (c == ' ' || c == '\t' || c == '\r') {
                if (c == '\t') {
                    col += TAB_WIDTH;
                } else if (c == ' ') {
                    col++;
                }
                i++;
                continue;
            }

            // newline
            if (c == '\n') {
                tokens.add(new Token(Token.Type.NEWLINE, "\n", null, line, col));
                i++; line++; col = 1;
                atLineStart = true;
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

        while (indentLevels.peek() > 0) {
            indentLevels.pop();
            // Close any still-open blocks before EOF.
            tokens.add(new Token(Token.Type.DEDENT, "<DEDENT>", null, line, 1));
        }

        tokens.add(new Token(Token.Type.EOF, "", null, line, col));
        return tokens;
    }

    private void handleLineIndentation() {
        int currentIndent = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ' ') {
                currentIndent++;
                i++;
                continue;
            }
            if (c == '\t') {
                currentIndent += TAB_WIDTH;
                i++;
                continue;
            }
            if (c == '\r') {
                i++;
                continue;
            }
            break;
        }

        if (i < s.length() && s.charAt(i) == '\n') {
            // Blank line: emit NEWLINE but do not modify indentation stack.
            tokens.add(new Token(Token.Type.NEWLINE, "\n", null, line, currentIndent + 1));
            i++;
            line++;
            col = 1;
            atLineStart = true;
            return;
        }

        int previousIndent = indentLevels.peek();
        if (currentIndent > previousIndent) {
            // Entering a deeper block level.
            indentLevels.push(currentIndent);
            tokens.add(new Token(Token.Type.INDENT, "<INDENT>", null, line, 1));
        } else if (currentIndent < previousIndent) {
            // Closing one or more nested block levels.
            while (indentLevels.peek() > currentIndent) {
                indentLevels.pop();
                tokens.add(new Token(Token.Type.DEDENT, "<DEDENT>", null, line, 1));
            }
            if (indentLevels.peek() != currentIndent) {
                throw new RuntimeException("Inconsistent indentation at line " + line);
            }
        }

        col = currentIndent + 1;
        atLineStart = false;
    }
}