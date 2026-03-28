package Tokenizer;

public class Token {
    public enum Type {  
        
        // NOTE: enumeration/enum is a special data type that allows a variable to be a set of predefined constants.
        
        // Literals and Identifiers
        NUMBER, STRING, IDENTIFIER,
        
        // Operators and Symbols (CALC specific)
        ASSIGN, PLUS, MINUS, MULTIPLY, DIVIDE,
        GT, LT, EQ, ARROW,
        
        // Keywords/Actions
        PRINT, IF, REPEAT,
        
        // Structural
        LPAREN, RPAREN, COLON,
        EOF, NEWLINE
    }
    
    // Private final fields ensure the data cannot be changed after creation
    private final Type type;
    private final String lexeme;
    private final Object literal;
    private final int line;
    private final int column;
    
    public Token(Type type, String lexeme, Object literal, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
        this.column = column;
    }

    // Getters provide read-only access to the fields
    public Type getType() { return type; }
    public String getLexeme() { return lexeme; }
    public Object getLiteral() { return literal; }
    public int getLine() { return line; }
    public int getColumn() { return column; }

    @Override
    public String toString() {
        return "Token{" + type + " '" + lexeme + "' at " + line + ":" + column + "}";
    }
}