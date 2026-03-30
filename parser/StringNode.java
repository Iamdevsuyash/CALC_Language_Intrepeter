public class StringNode implements Expression {
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    public Object evaluate() {
        return value;
    }
}