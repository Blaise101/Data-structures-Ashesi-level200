package revisions.algorithms;

public class Largest1 {
  public static void main(String[] args) {
    int num1 = 58;
    int num2 = 4;
    int num3 = 7;

    if(num1 > num2 && num1 > num3){
      System.out.println("Largest is: " + num1);
    }else if(num2 > num1 && num2 > num3){
      System.out.println("Largest is: " + num2);
    }else{
      System.out.println("Largest is: " + num3);
    }
  }
}
