import java.util.List;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        StringNode strNode = new StringNode("Hello, World!");
        System.out.println(strNode.evaluate(env)); 

        NumberNode numNode = new NumberNode(42.0);
        System.out.println(numNode.evaluate(env));

        Token token = new Token(Token.Type.IDENTIFIER, "myVar", null, 1, 1);
        System.out.println(token.type()); 
        System.out.println(token.lexeme()); 

        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(Token.Type.IDENTIFIER, "x", null, 1, 1));
        tokens.add(new Token(Token.Type.ASSIGN, ":=", null, 1, 3));
        tokens.add(new Token(Token.Type.NUMBER, "10", 10.0, 1, 5));
        tokens.add(new Token(Token.Type.PRINT, ">>", null, 1, 8));
        tokens.add(new Token(Token.Type.IDENTIFIER, "x", null, 1, 11));
        tokens.add(new Token(Token.Type.EOF, "", null, 1, 12));

        Parser parser = new Parser(tokens);
        List<Instruction> instructions = parser.parse();
        for(Instruction inst : instructions){
            System.out.println(inst);
        }

        Expression left = new StringNode("hello");
        Expression right = new NumberNode(10);
        Expression expr = new BinaryOpNode(left, "+", right);
        System.out.println(expr.evaluate(env));
    }
}