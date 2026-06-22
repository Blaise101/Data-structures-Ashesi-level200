package lectures.lecture7;

public class Arrays{
  public static void main(String[] args) {
    int[] myArray = new int[10];
    myArray[0] = 5;
    myArray[1] = 2;
    myArray[2] = 4;
    myArray[3] = 8;
    myArray[4] = 1;
    
    for (int item : myArray){
        System.out.print(" "+item);
    }
    for(int i=5; i<=9; i++){
      int temp = myArray[i];
      myArray[i] = myArray[9-i];
      myArray[9-i] = temp;
    }
    System.out.print("\n");
    for (int item : myArray){
        System.out.print(" "+item);
    }
  }
}