package assignment3;

public class MyQueue<T> {
  private MyStack<T> stackIn;
  private MyStack<T> stackOut;

  public MyQueue(){
    this.stackIn = new MyStack<>();
    this.stackOut = new MyStack<>();
  }

  public void enqueue(T element){
    stackIn.push(element);
  }

  public T dequeue(){
    if(stackOut.isEmpty()){
      while(!stackIn.isEmpty()){
        stackOut.push(stackIn.pop());
      }
    }
    if (stackOut.isEmpty()) {
      throw new RuntimeException("Queue Underflow");
    }
    return stackOut.pop();
  }

  public boolean isEmpty() {
    return stackIn.isEmpty() && stackOut.isEmpty();
  }

  public int size(){
    return stackIn.size() + stackOut.size();
  }
}
