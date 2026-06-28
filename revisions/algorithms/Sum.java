package revisions.algorithms;

public class Sum {
  public static void main(String[] args) {
    int[] numbers = {2,1,3,4,5,6,7,8};
    int sum = 0;

    for(int number: numbers){
      sum += number;
    }

    System.out.println("Sum is: "+sum);
  }
}
