// Node class for the custom Stack
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Custom Stack Implementation from Scratch
class MyStack<T> {
    private Node<T> top;
    private int count;

    public MyStack() {
        this.top = null;
        this.count = 0;
    }

    // Pushes an element onto the top of the stack
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
        count++;
    }

    // Removes and returns the top element
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow");
        }
        T data = top.data;
        top = top.next;
        count--;
        return data;
    }

    // Returns top element without removing
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return top.data;
    }

    // Returns true if stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Returns current number of elements
    public int size() {
        return count;
    }
}

// Custom Queue implemented STRICTLY using two instances of MyStack
class MyQueue<T> {
    private MyStack<T> stackIn;
    private MyStack<T> stackOut;

    public MyQueue() {
        this.stackIn = new MyStack<>();
        this.stackOut = new MyStack<>();
    }

    // Enqueue adds element to stackIn
    public void enqueue(T element) {
        stackIn.push(element);
    }

    // Transfers elements from stackIn to stackOut to preserve FIFO order
    private void shiftStacks() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }

    // Dequeue removes and returns element from front
    public T dequeue() {
        shiftStacks();
        if (stackOut.isEmpty()) {
            throw new RuntimeException("Queue Underflow");
        }
        return stackOut.pop();
    }

    // Returns true if both internal stacks are empty
    public boolean isEmpty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    // Sum of sizes of both internal stacks
    public int size() {
        return stackIn.size() + stackOut.size();
    }
}

public class ExpressionConverter {

    // Returns operator precedence
    private static int getPrecedence(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
            default: return -1;
        }
    }

    // Checks if token is an operator
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || 
               token.equals("/") || token.equals("^");
    }

    // Converts space-separated infix expression to postfix
    public static String convertInfixToPostfix(String infixExpression) {
        MyQueue<String> inputQueue = new MyQueue<>();
        MyQueue<String> outputQueue = new MyQueue<>();
        MyStack<String> operatorStack = new MyStack<>();

        // Split input by whitespace and enqueue tokens
        String[] tokens = infixExpression.trim().split("\\s+");
        for (String token : tokens) {
            if (!token.isEmpty()) {
                inputQueue.enqueue(token);
            }
        }

        // Execute Shunting-Yard algorithm
        while (!inputQueue.isEmpty()) {
            String token = inputQueue.dequeue();

            if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputQueue.enqueue(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop(); // Discard '('
                }
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek()) &&
                       getPrecedence(operatorStack.peek()) >= getPrecedence(token)) {
                    outputQueue.enqueue(operatorStack.pop());
                }
                operatorStack.push(token);
            } else {
                // Token is an operand
                outputQueue.enqueue(token);
            }
        }

        // Empty remaining operators to output queue
        while (!operatorStack.isEmpty()) {
            outputQueue.enqueue(operatorStack.pop());
        }

        // Build result string from output queue
        StringBuilder result = new StringBuilder();
        while (!outputQueue.isEmpty()) {
            result.append(outputQueue.dequeue()).append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        // Required test cases (Space-separated input)
        String[] testCases = {
            "A + B * C",
            "( A + B ) * C - D"
        };

        System.out.println("=================================================");
        System.out.println("          INFIX TO POSTFIX CONVERSION            ");
        System.out.println("=================================================");

        for (String test : testCases) {
            String postfix = convertInfixToPostfix(test);
            System.out.println("Infix  : " + test);
            System.out.println("Postfix: " + postfix);
            System.out.println("-------------------------------------------------");
        }
    }
}