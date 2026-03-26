import java.util.List;
import java.util.ArrayList;

public class Parser{
    private List<Token> tokens;
    private int index = 0;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
    }

    private Token peek() {
        return tokens.get(index);
    }

    private boolean isEnd() {
        return peek().getType() == TokenType.EOF; 
    }

    private Token advance(){
        if(!isEnd()){
            index++;
        }
        return tokens.get(index - 1);
    }

    private boolean check(TokenType type){
        if(isEnd()){
            return false;
        }
        return peek().getType() == type;
    }

    private boolean match(TokenType type){
        if(check(type)){
            advance();
            return true;
        }
        else{
            return false;
        }
    }

    public List<Instruction> parse(){
        List<Instruction> instructions = new ArrayList<>();
        while(!isEnd()){
            instructions.add(parseInstruction());
        }
        return instructions;
    }

    private Instruction parseInstruction(){
        if(check(TokenType.IDENTIFIER)){
            advance();
            return parseAssignment();
        }
        else if(check(TokenType.PRINT)){
            advance();
            return parsePrint();
        }
        else if(check(TokenType.QUESTION)){
            advance();
            return parseIf();
        }
        else if(check(TokenType.AT)){
            advance();
            return parseRepeat();
        }
        else{
            throw new RuntimeException("Unexpected token: " + peek().getValue());
        }
    }

    private Instruction parseAssignment(){
        String var = tokens.get(index - 1).getValue();
        if(!check(TokenType.ASSIGN)){
            throw new RuntimeException("Expected ':=' after identifier");
        }
        advance();
        Expression expr = parseExpression();
        return new AssignInstruction(var, expr);
    }

    private Expression parseExpression(){
        Expression left = parseTerm();
        while(check(TokenType.PLUS) || check(TokenType.MINUS)){
            Token operator = advance();
            Expression right = parseTerm();
            BinaryOpNode node = new BinaryOpNode(left, operator.getValue(), right);
            left = node;
        }
        return left;
    }

    private Expression parseTerm(){
        Expression left = parsePrimary();
        while(check(TokenType.MULTIPLY) || check(TokenType.DIVIDE)){
            Token operator = advance();
            Expression right = parsePrimary();
            BinaryOpNode node = new BinaryOpNode(left, operator.getValue(), right);
            left = node;
        }
        return left;
    }

    private Expression parsePrimary(){
        if(check(TokenType.NUMBER)){
            Token t = advance();
            return new NumberNode(Double.parseDouble(t.getValue()));
        }
        else if(check(TokenType.STRING)){
            Token t = advance();
            return new StringNode(t.getValue());
        }
        else if(check(TokenType.IDENTIFIER)){
            Token t = advance();
            return new VariableNode(t.getValue());
        }
        else{
            throw new RuntimeException("Unexpected token: " + peek().getValue());
        }
    }

    private Instruction parsePrint(){
        Expression expr = parseExpression();
        return new PrintInstruction(expr);
    }

    private Instruction parseIf(){
        Expression condition = parseExpression();
        if(!check(TokenType.ARROW)){
            throw new RuntimeException("Expected '=>' after condition");
        }
        advance();
        List<Instruction> body = new ArrayList<>();
        body.add(parseInstruction());
        return new IfInstruction(condition, body);
    }

    private Instruction parseRepeat(){
        Expression times = parseExpression();
        if(!check(TokenType.ARROW)){
            throw new RuntimeException("Expected '=>' after times expression");
        }
        advance();
        List<Instruction> body = new ArrayList<>();
        body.add(parseInstruction());
        return new RepeatInstruction(times, body);
    }
}