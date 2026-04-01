package parser;
import evaluator.Environment;

// Common interface for all expression nodes like NumberNode, StringNode, VariableNode, BinaryOpNode
public interface Expression {
    Object evaluate(Environment env); // evaluates the expression and returns its value (number, string, boolean)
}