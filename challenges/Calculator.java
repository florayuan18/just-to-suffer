import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.lang.Math;

// you can import the math for square root
// not allowed to import queue or stack
// this is used by importing the stacks function from the stack code
import LinkedLists.Stack;


public class Calculator {

    private final String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double calculatorResult = 0.0;

    // here we're setting up our variables


    private final Map<String, Integer> OPERATORS = new HashMap<>(); {
        OPERATORS.put("#", 3); //pyth
        OPERATORS.put("?", 3); // square
        OPERATORS.put("^", 3); // power
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);

        // determining our operators
    }


    private final Map<String, Integer> SEPARATORS = new HashMap<>(); {

        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);

        // determining our parenthesis
    }

// constructor with 4 this statements
    public Calculator(String expression) {

        this.expression = expression;
        this.termTokenizer();
        this.tokensToReversePolishNotation();
        this.rpnToResult();
    }


    private boolean isOperator(String token) { // checking token for being true or false as operator

        return OPERATORS.containsKey(token); // if true, is operator
    }


    private boolean isSeperator(String token) { // checking token for being true or false as separator

        return SEPARATORS.containsKey(token); // determines if the string is seperated into the separate tokens
    }

    // determining which one has more priority
    private Boolean isPrecedent(String token1, String token2) {

        return (OPERATORS.get(token1) - OPERATORS.get(token2) >= 0) ; // if true, token1 has more priority - comes first
    }

    private void termTokenizer() {

        // purpose of this is to split the string into tokens

        this.tokens = new ArrayList<>();

        int start = 0;
        StringBuilder multiCharTerm = new StringBuilder();
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if ( isOperator(c.toString() ) || isSeperator(c.toString())  ) {

                if (multiCharTerm.length() > 0) {
                    //here, we're adding the token to a stack
                    tokens.add(this.expression.substring(start, i)); // taking each character out and putting it into the arraylist
                }

                if (c != ' ') {
                    // just looking for spaces
                    tokens.add(c.toString());
                }

                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {

                multiCharTerm.append(c);
            }

        }

        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
        // System.out.println("tokens=" + tokens); // test code
    }

    // we need to know if its a seperator, or an operator. for these tests, we're going to build data structure .
    // these are the things that will help us tokenize and help us later on when we're doing our math expressions

    // Takes tokens and converts to Reverse Polish Notation (RPN), this is one where the operator follows its operands.
    private void tokensToReversePolishNotation () {

        this.reverse_polish = new ArrayList<>();

        Stack tokenStack = new Stack(); // tokenStack is an object of Stack
        for (String token : tokens) { // referenced later
            switch (token) {

                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (tokenStack.peek() != null && !tokenStack.peek().equals("(")) {
                        reverse_polish.add( (String)tokenStack.pop() );
                    }
                    tokenStack.pop();
                    break;
                case "*":
                case "/":
                case "+":
                case "-":
                case "%":
                case "^":

                    while (tokenStack.peek() != null && isOperator((String) tokenStack.peek())) { // checking if last number is not empty & is an operator
                        if ( isPrecedent(token, (String) tokenStack.peek() )) { // checking priority
                            reverse_polish.add((String)tokenStack.pop()); // pushing to stack
                            continue;
                        }
                        break;
                    }

                    tokenStack.push(token); // put the tokens in the stack
                    break;
                default:
                    this.reverse_polish.add(token);
            }
        }

        while (tokenStack.peek() != null) {
            reverse_polish.add((String)tokenStack.pop()); // last in first out -- put into the arraylist (using this)
        }

        // System.out.println("reverse_polish=" + reverse_polish); // test code
    }


    private void rpnToResult() {
        Stack stackCalculation = new Stack(); // renamed to stackCalculation for clarity

        for (String token : this.reverse_polish) {
            if (!isOperator(token)) {
                stackCalculation.push(token); // if not an operator, placed in stack
            }
            else {
                Double operand1 = Double.valueOf( (String)stackCalculation.pop() ); // changing string to double
                Double operand2 = Double.valueOf( (String)stackCalculation.pop() ); // in order to actually calculate
//                System.out.println("operand1=" + operand1 + " operand2=" + operand2); // test code - checking which is which

                Double myResult;

                switch (token) { // switch is better than if for multiple cases
                    case "*":
                        myResult = operand2 * operand1;
                        break;
                    case "/":
                        myResult = operand2 / operand1;
                        break;
                    case "+":
                        myResult = operand2 + operand1;
                        break;
                    case "-":
                        myResult = operand2 - operand1;
                        break;
                    case "%":
                        myResult = operand2 % operand1;
                        break;
                    case "^":
                        myResult = Math.pow(operand2, operand1);
                        break;
                    default:
                        myResult = 0.00; // two decimals
                }
                stackCalculation.push( String.valueOf( myResult )); // final result pushed into stack
            } // else continued offscreen
        } //for loop

        this.calculatorResult = Double.valueOf((String)stackCalculation.pop());
    }

    // toString print
    public String toString() {
        return ("Original expression: " + this.expression + "\n" +
                "Tokenized expression: " + this.tokens.toString() + "\n" +
                "Reverse Polish Notation: " +this.reverse_polish.toString() + "\n" +
                "Final result: " + String.format("%.2f", this.calculatorResult));
    }


    public static void main(String[] args) {

        // 1. Simple Math
        Calculator simpleMath = new Calculator("100 + 200  * 3");
        System.out.println("Simple Math\n" + simpleMath);
        System.out.println();

        // 2. Parenthesis Math
        Calculator parenthesisMath = new Calculator("(100 + 200)  * 3"); // The stuff in the parenthesis is a token
        System.out.println("Parenthesis Math\n" + parenthesisMath);
        System.out.println();

        // 3. Fraction Math
        Calculator fractionMath = new Calculator("100.2 - 99.3");
        System.out.println("Fraction Math\n" + fractionMath);
        System.out.println();

        // 4. Modulo Math
        Calculator moduloMath = new Calculator("300 % 200");
        System.out.println("Modulo Math\n" + moduloMath);
        System.out.println();

        // 5. Division Math
        Calculator divisionMath = new Calculator("300/200");
        System.out.println("Division Math\n" + divisionMath);
        System.out.println();

        // 6. Multiplication Math
        Calculator multiplicationMath = new Calculator("300 * 200");
        System.out.println("Multiplication Math\n" + multiplicationMath);
        System.out.println();

        // 7. All Math
        Calculator allMath = new Calculator("200 % 300 + 5 + 300 / 200 + 1 * 100");
        System.out.println("All Math\n" + allMath);
        System.out.println();

        // 8. All Math 2
        Calculator allMath2 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100");
        System.out.println("All Math2\n" + allMath2);
        System.out.println();

        // 9. All Math 3
        Calculator allMath3 = new Calculator("200%(300+5+300)/200+1*100");
        System.out.println("All Math3\n" + allMath3);
        System.out.println();

        // 10. Power of Operators
        Calculator squareMath = new Calculator("4 ^ 2");
        System.out.println("squareMath\n" + squareMath);
        System.out.println();
    }
}