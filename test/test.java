package test;

import evaluator.Environment;
import evaluator.Instruction;
import evaluator.PrintInstruction;
import parser.BinaryOpNode;
import parser.Expression;
import parser.NumberNode;

public class test {
    public static void main(String[] args) {
        NumberNode n1 = new NumberNode(54);
        NumberNode n2 = new NumberNode(43);
//
        BinaryOpNode b1 = new BinaryOpNode("==",n1,n2);
        System.out.println(b1.evaluate(new Environment()));

        Instruction print = new PrintInstruction(new NumberNode(54));
        print.execute(new Environment());

    }
}
