import java.util.Scanner;

public class greaterNumbers {
 double[] getNumbers(double[] numbers, double n){
  double[] greaterNumbers = new double[numbers.length];
  int count = 0;
  for (double number : numbers){
    if (number > n){
      greaterNumbers[count] = number;
      count++;
    }
  }
  return greaterNumbers;
 }

 public static void main(String[] args){

  greaterNumbers gn = new greaterNumbers();

  double[] numbers = {1.5, 2.3, 3.7, 4.1, 5.6};

  Scanner scanner = new Scanner(System.in);
  System.out.print("Enter a number: ");
  double n = scanner.nextDouble();
  double[] result =  gn.getNumbers(numbers, n);

  scanner.close();
  System.out.println("Numbers greater than " + n + ":");
  for (double number : result){
    if (number != 0){
      System.out.println(number);
    }
  }
 }
}
