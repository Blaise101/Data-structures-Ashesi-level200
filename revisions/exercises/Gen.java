package revisions.exercises;

public class Gen <K, V> {
  private K objk;
  private V objv;

  public Gen(K objk, V objv){
    this.objk = objk;
    this.objv = objv;
  }

  public K getK(){return objk;}
  public V getV(){return objv;}

  public static void main(String[] args) {
    Gen<String,Integer> myData = new Gen<>("Blaise", 22);
    
    String name = myData.getK();
    int age = myData.getV();

    System.out.println("I am "+name+" and I'm "+age+" years old");
  }
}
