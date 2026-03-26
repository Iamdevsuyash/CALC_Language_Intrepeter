public class VariableNode implements Expression {
    private String name;

    public VariableNode(String name) {
        this.name = name;
    }

    public Object evaluate() {
        return null; 
    }
}