package revisions.exercises;

import java.util.ArrayList;

public class CollectionUtils {

  public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list){
    ArrayList<E> uniqueList = new ArrayList<>();

    for(E element: list){
      if(!uniqueList.contains(element)){
        uniqueList.add(element);
      }
    }

    return uniqueList;
  }

  public static void main(String[] args) {
    ArrayList<Integer> numbers = new ArrayList<>();
    numbers.add(1);
    numbers.add(2);
    numbers.add(2);
    numbers.add(3);
    numbers.add(1);

    ArrayList<Integer> uniqueNumbers = removeDuplicates(numbers);
    System.out.println(uniqueNumbers); // Should print [1, 2, 3]
  }
}
