package tokenizer;

import java.util.*;


// Tokenizer class that takes a string and produces a list of tokens
public class Tokenizer {
    private final String s;
    private int i = 0, line = 1, col = 1;
    private final List<Token> tokens = new ArrayList<>();


    //define the two character tokens and their corresponding types
    private static final Map<String, Token.Type> twoChar = Map.of(
       
    );


    // define the one character tokens and their corresponding types
    private static final Map<Character, Token.Type> oneChar = Map.ofEntries(
        
    );

}