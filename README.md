# CALC Programming Language Interpreter

A simple interpreter for a custom CALC programming language. This project implements a tokenizer, parser, and evaluator for a small language with variables, arithmetic, printing, conditionals, and loops.

## Project Overview

- `Interpreter.java` - entry point that reads a source file, tokenizes it, parses it into instructions, and evaluates the program.
- `tokenizer/Tokenizer.java` - converts source text into tokens, handling indentation-based blocks, strings, numbers, identifiers, operators, and keywords.
- `parser/Parser.java` - constructs an instruction list from tokens and builds expression trees.
- `evaluator/Evaluator.java` - executes parsed instructions in an environment.
- `evaluator/Environment.java` - stores variable values during execution.
- `evaluator/*.java` - instruction implementations for assignment, print, if, and repeat statements.
- `parser/*.java` - AST expression types for numbers, strings, variables, and binary operations.


## Language Features

### Statements

- Variable assignment: `x := 10`
- Print output: `>> x + 5`
- Conditional execution: `? condition =>` followed by an indented block
- Repeat loop: `@ count =>` followed by an indented block

### Expressions

- Numeric literals: `42`, `3.14`
- String literals: `"hello"`
- Variables: `x`, `result`
- Arithmetic: `+`, `-`, `*`, `/`
- Comparison: `>`, `<`, `==`

### Block structure

The language uses indentation to delimit blocks, similar to Python. After `? condition =>` or `@ count =>`, an indented block is required.

## Example

Sample program from `test.calc`:

```calc
x := 10
y := 3
result := x + y * 2
>> result - 5
? x-5<2 =>
    >> "pass"
```

This program:

1. Defines `x` and `y`
2. Computes `result`
3. Prints the value of `result - 5`
4. Evaluates a conditional and prints `"pass"` if true

## How to Run

Compile the project with `javac` from the repository root:

```bash
javac Interpreter.java 
```

Run the interpreter with a CALC source file:

```bash
java Interpreter test.calc
```

## Project Structure

- `Interpreter.java` - main entry point
- `tokenizer/` - tokenization system handling indentation, operators, and literals
- `parser/` - parser and AST node implementations
- `evaluator/` - runtime evaluator and instructions
- `test/` - Java tests for parser, tokenizer, and evaluator
- `test.calc` - example CALC source file

## Notes

- `>>` is the print operator.
- `?:` is not used; conditionals are started with `?`.
- `@` starts repeat loops.
- Strings must be wrapped in double quotes.
- Division by zero throws an exception.
- Undefined variables throw runtime errors.

## Future Improvements

- Add `else` support for conditionals
- Add parentheses for expression precedence control
- Add boolean literals and more comparison operators
- Improve error reporting with line/column details

---

If you want, I can also add a dedicated `docs/` folder with syntax reference and design notes.
