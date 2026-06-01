public class Gen<T> {
  T object; 
  Gen(T object) {
    this.object = object;
  }
  T getObject() {
    return this.object;
  }

  void showType() {
    System.out.println("Type of T is " + object.getClass().getName());
  }

  public static void main(String[] args){
    Gen<Integer> intObj = new Gen<>(42);
    intObj.showType();
    System.out.println("Value: " + intObj.getObject());

    Gen<String> strObj = new Gen<>("Hello, Remy!");
    strObj.showType();
    System.out.println("Value: " + strObj.getObject());

  }
}

