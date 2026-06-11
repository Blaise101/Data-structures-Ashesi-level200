package revisions.exercises;

public class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public Object getContent() {
        // Hint: This will need to return the generic type instead of Object
        return content; 
    }

    public static void main(String[] args) {

      // Box specific for integers
      Box<Integer> integerBox = new Box<>(50);
      int value = (int) integerBox.getContent();
      System.out.println("The integer box contains: " + value);
      
      // Box specific for strings
      Box<String> myString = new Box<>("Blaise");
      String name = (String) myString.getContent();
      System.out.println("My name is " + name);

    }
}
