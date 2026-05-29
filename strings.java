public class strings {
  public static void main(String[] args){
    String name = new String("Blaise Izerimana");
    String name2 = "Blaise Kamali";

    System.out.println(name.concat(" ").concat(name2));
    System.out.println(name.charAt(5));
    System.out.println(name.substring(0, 5));

    System.out.println(name.equals(name2));
    System.out.println(name.compareTo(name2));

    String formatted = String.format("Hello, %s! You are %d years old.", "John", 30);
    System.out.println(formatted);

    StringBuilder sb = new StringBuilder(); //creation
    int num = 42;
    sb.append("The answer is: "); //Appending a String
    sb.append(num); //Appending an integer
    System.out.println(sb);

    StringBuilder newsb = new StringBuilder(50);
    newsb.append("Hello everybody, I'm Blaise").insert(9, ".").delete(16 , 28);
    System.out.println(newsb);
  }
}