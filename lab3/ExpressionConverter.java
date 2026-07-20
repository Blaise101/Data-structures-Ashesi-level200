package lab3;

import java.util.ArrayList;
import java.util.List;

public class ExpressionConverter {

  // Helper: Determine operator precedence
  private static int getPrecedence(String operator) {
    switch (operator) {
      case "+": case "-": return 1;
      case "*": case "/": return 2;
      case "^": return 3;
      default: return -1;
    }
  }

  // Helper: Check if a token is an operator
  private static boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || 
        token.equals("/") || token.equals("^");
  }

  // Robust Tokenizer to safely handle multi-character variables, spaces, and decimals
  private static List<String> tokenize(String expression) {
    List<String> tokens = new ArrayList<>();
    int i = 0;
    while (i < expression.length()) {
      char c = expression.charAt(i);
      if (Character.isWhitespace(c)) {
        i++;
        continue;
      }
      if (isOperator(String.valueOf(c)) || c == '(' || c == ')') {
        tokens.add(String.valueOf(c));
        i++;
      } else {
        // Parse full numbers (including decimals) or multi-letter variables
        StringBuilder sb = new StringBuilder();
        while (i < expression.length() && (Character.isLetterOrDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
          sb.append(expression.charAt(i));
          i++;
        }
        tokens.add(sb.toString());
      }
    }
    return tokens;
  }

  // PART B: Infix-to-Postfix Conversion
  public static String infixToPostfix(String infixExpression) {
    List<String> tokens = tokenize(infixExpression);
    CustomStack<String> stack = new CustomStack<>();
    CustomQueue<String> outputQueue = new CustomQueue<>();

    for (String token : tokens) {
      if (!isOperator(token) && !token.equals("(") && !token.equals(")")) {
        // Token is an operand
        outputQueue.enqueue(token);
      } else if (token.equals("(")) {
        stack.push(token);
      } else if (token.equals(")")) {
        while (!stack.isEmpty() && !stack.peek().equals("(")) {
          outputQueue.enqueue(stack.pop());
        }
        if (!stack.isEmpty() && stack.peek().equals("(")) {
          stack.pop(); // Discard matching '('
        }
      } else if (isOperator(token)) {
        while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(token)) {
          outputQueue.enqueue(stack.pop());
        }
        stack.push(token);
      }
    }

    while (!stack.isEmpty()) {
      outputQueue.enqueue(stack.pop());
    }

    // Build final postfix string from Queue
    StringBuilder result = new StringBuilder();
    while (!outputQueue.isEmpty()) {
      result.append(outputQueue.dequeue()).append(" ");
    }
    return result.toString().trim();
  }

  // PART C: Postfix-to-Infix Reversal
  public static String postfixToInfix(String postfixExpression) {
    String[] tokens = postfixExpression.split("\\s+");
    CustomStack<String> stack = new CustomStack<>();

    for (String token : tokens) {
      if (token.isEmpty()) continue;
      
      if (!isOperator(token)) {
        // Token is an operand
        stack.push(token);
      } else if (isOperator(token)) {
        if (stack.size() < 2) throw new IllegalArgumentException("Invalid Expression: Too few operands.");
        String op2 = stack.pop(); // Right operand
        String op1 = stack.pop(); // Left operand
        String combined = "(" + op1 + " " + token + " " + op2 + ")";
        stack.push(combined);
      }
    }

    if (stack.size() != 1) throw new IllegalArgumentException("Invalid Expression: Too many operands left over.");
    return stack.pop();
  }

  // Main execution method running mandated test cases
  public static void main(String[] args) {
    String[] testCases = {
      "A + B * C - D",
      "(A + B) * (C - D)",
      "A * B + C / D",
      "a + b * c + ( d * e + f ) * g",
      "( 5 + 3 ) * 2 ^ 2 / ( 9 - 1 )",
      "4.99 * 1.06 + 5.99 + 6.99 * 1.06"
    };

    System.out.println("========================================================================");
    System.out.println("                  INFIX -> POSTFIX -> REVERSED INFIX                    ");
    System.out.println("========================================================================");

    for (int i = 0; i < testCases.length; i++) {
      String originalInfix = testCases[i];
      String postfix = infixToPostfix(originalInfix);
      String reversedInfix = postfixToInfix(postfix);

      System.out.printf("Test Case %d:\n", i + 1);
      System.out.println("  Original Infix  : " + originalInfix);
      System.out.println("  Postfix Output  : " + postfix);
      System.out.println("  Reversed Infix  : " + reversedInfix);
      System.out.println("------------------------------------------------------------------------");
    }
  }
}