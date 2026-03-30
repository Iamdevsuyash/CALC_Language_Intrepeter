// package tokenizer;

public record Token(Type type, String lexeme, Object literal, int line, int column) {

    public enum Type {
        // Literals
        NUMBER, STRING, IDENTIFIER,

        // Arithmetic operators
        PLUS, MINUS, MULTIPLY, DIVIDE,

        // Comparison operators
        GT, LT, EQ,

        // CALC-specific symbols
        ASSIGN,   // :=
        ARROW,    // =>
        PRINT,    // >>
        IF,       // ?
        REPEAT,   // @

        // Structural
        LPAREN, RPAREN, COLON,
        NEWLINE, EOF
    }

    @Override
    public String toString() {
        return "Token{" + type + " '" + lexeme + "' at " + line + ":" + column + "}";
    }
}