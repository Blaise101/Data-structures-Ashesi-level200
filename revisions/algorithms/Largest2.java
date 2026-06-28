package revisions.algorithms;

public class Largest2 {
  public static void main(String[] args) {
    int[] numbers = {2,1,3,4,53,6,7,8};
    int largest = numbers[0];

    for(int number: numbers){
      if (largest < number){
        largest = number;
      }
    }

    System.out.println("Largest is: "+largest);
  }
}
