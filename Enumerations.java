public class Enumerations {
  enum level {
    LOW("Low Level"),
    MEDIUM("Medium Level"),
    HIGH("High Level");

    private String description;

    private level(String description) {
      this.description = description;
    }

    private String getDescription() {
      return this.description;
    }
  }
  public static void main (String[] args){
    level myLevel = level.HIGH;
    System.out.println("My level is: " + myLevel);
    System.out.println("Description: " + myLevel.getDescription());
  }
}

// primitive data types: byte, short, int, long, float, double, char, boolean
// non-primitive data types: String, Arrays, Classes, Interfaces, Enums