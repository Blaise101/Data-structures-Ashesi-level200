package revisions.exercises;

public class ArrayUtils {

    public static <E extends Comparable<E>> E max(E[] list) {
        E maxVal = list[0]; 
        
        for (int i = 1; i < list.length; i++) {
            if(list[i].compareTo(maxVal) > 0){
              maxVal = list[i];
            }
        }
        
        return maxVal;
    }

    public static void main(String[] args) {
        Integer[] numbers = {4, 12, 1, 8, 3};
        System.out.println("Max number: " + max(numbers)); // Should print 12

        String[] names = {"Alice", "Charlie", "Bob"};
        System.out.println("Max string: " + max(names));
    }
}