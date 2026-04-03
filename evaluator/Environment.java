package evaluator;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    // Use a Map<String, Object> to store variable names and their values.

    Map<String,Object> env = new HashMap<>();

    public void set(String name, Object value) {

        // Store or update the value for the given variable name.

        env.put(name,value);
    }
    public Object get(String name) throws RuntimeException{

        // Return the current value of the variable.
        // If the variable has not been defined, throw a RuntimeException
        // with a helpful message such as "Variable not defined: x".

        if (env.containsKey(name)){
            return env.get(name);
        }

        else{
            throw new RuntimeException("Variable not defined: "+name);
        }
    }

}
