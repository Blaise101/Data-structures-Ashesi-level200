package revisions;

import java.util.ArrayList;

public class CollectionGen {

  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();
    
    names.add("Blaise");
    names.add("Fida");

    System.out.println(names.get(1)); // no type casting
  }
  
}
