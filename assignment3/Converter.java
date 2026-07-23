package assignment3;

public class Converter {
  
  private static int getPrecedence(String operation){
    switch(operation){
      case "+": case "-": return 1;
      case "*": case "/": return 2;
      case "^": return 3;
      default: return -1;
    }
  }

  private static boolean isOperator(String exp){
    return exp.equals("+") || exp.equals("-") || exp.equals("*") || exp.equals("/") || exp.equals("^");
  }

  public static String convertInfixToPostfix(String infixExpression){
    MyQueue<String> inputQueue = new MyQueue<>();
    MyQueue<String> outputQueue = new MyQueue<>();
    MyStack<String> operatorsStack = new MyStack<>();

    String[] operators = infixExpression.trim().split("\\s+");
    for(String operator : operators){
      if(!operator.isEmpty()){
        inputQueue.enqueue(operator);
      }
    }

    // Shunting-Yard Algorithm
    while(!inputQueue.isEmpty()){
      String element = inputQueue.dequeue();
      if(element.equals("(")){
        operatorsStack.push(element);
      }else if(element.equals(")")){
        while(!operatorsStack.isEmpty() && !operatorsStack.peek().equals("(")){
          outputQueue.enqueue(operatorsStack.pop());
        }
        if (!operatorsStack.isEmpty() && operatorsStack.peek().equals("(")) {
          operatorsStack.pop();
        }
      }else if(isOperator(element)){
        while (!operatorsStack.isEmpty() && isOperator(operatorsStack.peek()) && getPrecedence(operatorsStack.peek()) >= getPrecedence(element)) {
            outputQueue.enqueue(operatorsStack.pop());
        }
        operatorsStack.push(element);
      }else{
        // The element is an operand
        outputQueue.enqueue(element);
      }
    }

    while(!operatorsStack.isEmpty()){
      outputQueue.enqueue(operatorsStack.pop());
    }

    // Build result string from output queue
    StringBuilder result = new StringBuilder();
    while (!outputQueue.isEmpty()) {
        result.append(outputQueue.dequeue()).append(" ");
    }

    return result.toString().trim();

  }

  public static void main(String[] args){
    String[] cases = {"A + B * C", "( A + B ) * C - D"};

    System.out.println("-------------------------------------------------");
    System.out.println("          INFIX TO POSTFIX CONVERSION            ");
    System.out.println("-------------------------------------------------");

    for(String c : cases){
      String postfix = convertInfixToPostfix(c);
      System.out.println("Infix    : " + c);
      System.out.println("Postfix  : " + postfix);
      System.out.println("-----------------------------------------------");
    }
  }
}
