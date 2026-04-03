package parser;
import evaluator.Environment;

// Represents a variable reference in the expression
public class VariableNode implements Expression {
    private String name; // name of the variable

    public VariableNode(String name) { // initializes the variable name
        this.name = name;
    }
    @Override
    public Object evaluate(Environment env) { 
        // return null; // Variable evaluation is not implemented yet, returns null for now
        // if(env.containsKey(name)){
        //     return env.get(name); // returns the value of the variable from the environment
        // }
        // else{
        //     throw new RuntimeException("Undefined variable: " + name); // throws an exception if the variable is not defined 
        // }
        return env.get(name);
    }
}