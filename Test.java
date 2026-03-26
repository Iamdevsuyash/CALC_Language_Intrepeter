import java.util.List;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        StringNode strNode = new StringNode("Hello, World!");
        System.out.println(strNode.evaluate()); 

        NumberNode numNode = new NumberNode(42.0);
        System.out.println(numNode.evaluate());

        Token token = new Token(TokenType.IDENTIFIER, "myVar");
        System.out.println(token.getType()); 
        System.out.println(token.getValue()); 

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.IDENTIFIER, "x"));
        tokens.add(new Token(TokenType.ASSIGN, ":="));
        tokens.add(new Token(TokenType.NUMBER, "10"));
        tokens.add(new Token(TokenType.PRINT, ">>"));
        tokens.add(new Token(TokenType.IDENTIFIER, "x"));
        tokens.add(new Token(TokenType.EOF, ""));

        Parser parser = new Parser(tokens);
        List<Instruction> instructions = parser.parse();
        for(Instruction inst : instructions){
            System.out.println(inst);
        }
    }
}