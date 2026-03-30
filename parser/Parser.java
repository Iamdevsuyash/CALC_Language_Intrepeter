package parser;

import java.util.List;
import java.util.ArrayList;
import tokenizer.Token;

public class Parser{
    private List<Token> tokens;
    private int index = 0;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
    }

    private Token peek() {
        if(index >= tokens.size()){
            return tokens.get(tokens.size() - 1);
        }
        return tokens.get(index);
    }

    private boolean isEnd() {
        return peek().type() == Token.Type.EOF; 
    }

    private Token advance(){
        if(!isEnd()){
            index++;
        }
        return tokens.get(index - 1);
    }

    private boolean check(Token.Type type){
        if(isEnd()){
            return false;
        }
        return peek().type() == type;
    }

    private boolean match(Token.Type type){
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
            if(check(Token.Type.NEWLINE)){
                advance();
                continue;
            }
            instructions.add(parseInstruction());
        }
        return instructions;
    }

    private Instruction parseInstruction(){
        if(check(Token.Type.IDENTIFIER)){
            advance();
            return parseAssignment();
        }
        else if(check(Token.Type.PRINT)){
            advance();
            return parsePrint();
        }
        else if(check(Token.Type.IF)){
            advance();
            return parseIf();
        }
        else if(check(Token.Type.REPEAT)){
            advance();
            return parseRepeat();
        }
        else{
            throw new RuntimeException("Unexpected token: " + peek().lexeme());
        }
    }

    private Instruction parseAssignment(){
        String var = tokens.get(index - 1).lexeme();
        if(!check(Token.Type.ASSIGN)){
            throw new RuntimeException("Expected ':=' after identifier");
        }
        advance();
        Expression expr = parseExpression();
        return new AssignInstruction(var, expr);
    }

    private Expression parseExpression(){
        Expression left = parseTerm();
        while(check(Token.Type.PLUS) || check(Token.Type.MINUS) || check(Token.Type.GT) || check(Token.Type.LT) || check(Token.Type.EQ)){
            Token operator = advance();
            Expression right = parseTerm();
            BinaryOpNode node = new BinaryOpNode(left, operator.lexeme(), right);
            left = node;
        }
        return left;
    }

    private Expression parseTerm(){
        Expression left = parsePrimary();
        while(check(Token.Type.MULTIPLY) || check(Token.Type.DIVIDE)){
            Token operator = advance();
            Expression right = parsePrimary();
            BinaryOpNode node = new BinaryOpNode(left, operator.lexeme(), right);
            left = node;
        }
        return left;
    }

    private Expression parsePrimary(){
        if(check(Token.Type.NUMBER)){
            Token t = advance();
            return new NumberNode((Double) t.literal());
        }
        else if(check(Token.Type.STRING)){
            Token t = advance();
            return new StringNode((String) t.literal());
        }
        else if(check(Token.Type.IDENTIFIER)){
            Token t = advance();
            return new VariableNode(t.lexeme());
        }
        else{
            throw new RuntimeException("Unexpected token: " + peek().lexeme());
        }
    }

    private Instruction parsePrint(){
        Expression expr = parseExpression();
        return new PrintInstruction(expr);
    }

    private Instruction parseIf(){
        Expression condition = parseExpression();
        if(!check(Token.Type.ARROW)){
            throw new RuntimeException("Expected '=>' after condition");
        }
        advance();
        List<Instruction> body = new ArrayList<>();
        body.add(parseInstruction());
        return new IfInstruction(condition, body);
    }

    private Instruction parseRepeat(){
        Expression times = parseExpression();
        if(!check(Token.Type.ARROW)){
            throw new RuntimeException("Expected '=>' after times expression");
        }
        advance();
        List<Instruction> body = new ArrayList<>();
        body.add(parseInstruction());
        return new RepeatInstruction(times, body);
    }
}