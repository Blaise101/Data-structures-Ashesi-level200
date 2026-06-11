package revisions;

import java.util.ArrayList;

public class CollectionNoGen {

  public static void main(String[] args) {
    ArrayList myArrayList = new ArrayList();
    myArrayList.add("Blaise");
    myArrayList.add(2563); // No type safety

    String name = (String) myArrayList.get(0); // required manual type casting

    System.out.println(name);
  }
  
}
