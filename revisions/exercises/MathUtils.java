package revisions.exercises;

public class MathUtils {
  
  public static <T extends Number> double add(T num1, T num2){
    return num1.doubleValue() + num2.doubleValue();
  }

  public static void main(String[] args) {
        System.out.println(add(5, 4.5));
        System.out.println(add(10L, 20f));
    }
}
